package com.mycompany.dao;

import com.mycompany.data.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

    private static final ReservationDao instance = new ReservationDao();

    private ReservationDao() {}

    public static ReservationDao getInstance() {
        return instance;
    }

    // ================== FIND ALL ==================
    public List<Reservation> findAll(Connection con) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                reservations.add(mapRow(rs));
            }
        }
        return reservations;
    }
    
    // ================== CHECK SLOT AVAILABILITY ==================
public boolean isSlotAvailable(int slotId, Connection con) throws SQLException {

    String sql =
        "SELECT COUNT(*) FROM reservations " +
        "WHERE slot_id = ? AND status = 'ACTIVE'";

    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, slotId);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0; // nema aktivnih rezervacija
            }
        }
    }
    return false;
}


    // ================== FIND BY ID ==================
    public Reservation findById(int id, Connection con) throws SQLException {
        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    // ================== INSERT ==================
    public int insert(Reservation reservation, Connection con) throws SQLException {

        String sql =
             "INSERT INTO reservations (user_id, slot_id, status, series_id) " +
             "VALUES (?, ?, ?, ?)";


        try (PreparedStatement ps =
                 con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, reservation.getUserId());
            ps.setInt(2, reservation.getSlotId());
            ps.setString(3, reservation.getStatus());
            ps.setObject(4, reservation.getSeriesId(), Types.INTEGER);
            


            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    // ================== UPDATE ==================
    public void update(Reservation reservation, Connection con) throws SQLException {

        String sql =
                 "UPDATE reservations " +
                 "SET user_id = ?, slot_id = ?, status = ?, series_id = ? " +
                 "WHERE reservation_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservation.getUserId());
            ps.setInt(2, reservation.getSlotId());
            ps.setString(3, reservation.getStatus());
           ps.setObject(4, reservation.getSeriesId(), Types.INTEGER);
            ps.setInt(5, reservation.getId());

            ps.executeUpdate();
        }
    }

    // ================== DELETE ==================
    public void delete(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // ================== FIND BY USER ==================
    public List<Reservation> findByUserId(int userId, Connection con) throws SQLException {

        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE user_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    reservations.add(mapRow(rs));
                }
            }
        }
        return reservations;
    }

    // ================== MAP ROW ==================
    private Reservation mapRow(ResultSet rs) throws SQLException {
        return new Reservation(
            rs.getInt("reservation_id"),
            rs.getInt("user_id"),
            rs.getInt("slot_id"),
            rs.getString("status"),
            rs.getObject("series_id", Integer.class)
        );
    }
}
