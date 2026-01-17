package com.mycompany.service;

import com.mycompany.dao.ResourceManager;
import com.mycompany.dao.UserDao;
import com.mycompany.data.User;
import com.mycompany.exception.ReservationException;
import java.sql.Connection;



public class AuthService {
     

    private static final AuthService instance = new AuthService();
    private UserDao userDao = UserDao.getInstance();

    private AuthService() {}

    public static AuthService getInstance() {
        return instance;
    }

    public User login(String username, String password) throws Exception {
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            User user = userDao.findByUsername(username, con);

            if (user == null || !user.getPassword().equals(password)) {
                return null;
            }
            return user;
        } finally {
            ResourceManager.closeConnection(con);
        }
    }
}
