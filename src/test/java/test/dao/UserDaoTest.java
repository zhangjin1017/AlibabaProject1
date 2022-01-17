package test.dao;

import com.zj.dao.UserDao;
import com.zj.domain.User;
import org.junit.jupiter.api.Test;

public class UserDaoTest {
    @Test
    public void findByUsername(){
        UserDao userDao = new UserDao();
        User user=userDao.findByUsername("李四");
        System.out.println(user);
    }
    @Test
    public void addUser(){
        UserDao userDao = new UserDao();
        User user=new User();
        user.setUsername("李四");
        user.setPassword("lisi");
        userDao.addUser(user);
    }
}
