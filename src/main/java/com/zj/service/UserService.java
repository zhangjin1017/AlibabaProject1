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
}
