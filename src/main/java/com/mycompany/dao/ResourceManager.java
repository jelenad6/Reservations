package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.exception.ReservationException;

public class ResourceManager {

    static {
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

   public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/business_centar?user=root&password=");
        return con;
    }
    public static void closeResources(ResultSet rs, PreparedStatement ps) {
        try { if (rs != null) rs.close(); } catch (Exception ignored) {}
        try { if (ps != null) ps.close(); } catch (Exception ignored) {}
    }

    public static void closeConnection(Connection con) throws ReservationException {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                throw new ReservationException("Failed to close database connection.", ex);
            }
        }
    }
}
