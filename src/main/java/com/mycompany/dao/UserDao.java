package com.mycompany.dao;

import com.mycompany.data.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private static final UserDao instance = new UserDao();

    private UserDao() {}

    public static UserDao getInstance() {
        return instance;
    }

    public User findByUsername(String username, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=?"
            );
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("full_name")
                );
            }
        } finally {
            ResourceManager.closeResources(rs, ps);
        }
        return user;
    }
}
