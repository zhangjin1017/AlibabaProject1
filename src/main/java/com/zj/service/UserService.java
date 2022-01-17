package com.zj.service;

import com.zj.dao.UserDao;
import com.zj.domain.User;

public class UserService {
    private UserDao userDao=new UserDao();
    public void register(User user) throws UserException {
        User user1 =userDao.findByUsername(user.getUsername());
        if (user1 != null) {
            throw new UserException("用户名"+user.getUsername() + ",被注册过了!");
        }
        userDao.addUser(user);
    }
    public User login(User form) throws UserException {
        User user =userDao.findByUsername(form.getUsername());
        if (user == null) {
            throw new UserException("用户名不存在!");
        }
        if (!form.getPassword().equals(user.getPassword())){
            throw new UserException("密码错误!");
        }

        return user;
    }
}
