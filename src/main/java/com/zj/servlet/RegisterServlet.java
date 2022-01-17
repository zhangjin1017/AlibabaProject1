package com.zj.servlet;

import com.zj.commons.CommonUtils;
import com.zj.domain.User;
import com.zj.service.UserService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        UserService userService=new UserService();
        User form= null;
        try {
            form = CommonUtils.toBean(request.getParameterMap(), User.class);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        Map<String,String> errors = new HashMap<String,String>();
        String username = form.getUsername();//获取表里的 usexname.
        if (username ==null||username.trim().isEmpty()) {
            errors.put ( "username" ,"用户名不能为空!");
        } else if(username.length() < 3||username.length() > 15){
            errors.put ( "username","用户名长度必须在3~15之间!");
        }
        String password = form.getPassword();//获取表里的 usexname.
        if (password ==null||password.trim().isEmpty()) {
            errors.put ( "password" ,"密码不能为空!");
        } else if(password.length() < 3||password.length() > 15){
            errors.put ( "password","密码长度必须在3~15之间!");
        }

        String sessionVerifyCode= (String) request.getSession().getAttribute("session_vcode");
        String verifyCode = form.getVerifyCode();//获取表里的 usexname.
        if (verifyCode ==null||verifyCode.trim().isEmpty()) {
            errors.put ( "verifyCode" ,"验证码不能为空!");
        } else if(verifyCode.length() !=4){
            errors.put ( "verifyCode","验证码长度必须为4!");
        }else if (!sessionVerifyCode.equalsIgnoreCase(verifyCode)) {
            errors.put ( "verifyCode","验证码错误!");
        }

        if(errors!=null&&errors.size()>0){
            request.setAttribute("errors",errors);
            request.setAttribute("user",form);
            request.getRequestDispatcher("/user/register.jsp").forward(request,response);
            return;
        }

        try {
            userService.register(form);
            response.getWriter().print("<h1>注册成功</h1><a href='"+request.getContextPath()+"/user/login.jsp'>点击这里去登录</a>");
        }catch (Exception e) {
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
        }
    }
}
