package com.zj.servlet;

import com.zj.commons.CommonUtils;
import com.zj.domain.User;
import com.zj.service.UserException;
import com.zj.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        UserService userService=new UserService();
        User form=null;
        try {
            form= CommonUtils.toBean(request.getParameterMap(), User.class);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            User user=userService.login(form);
            request.getSession().setAttribute("userSession",user);
            response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
        } catch (UserException e) {
            request.setAttribute("msg",e.getMessage());
            request.setAttribute("user",form);
            request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        }

    }
}
