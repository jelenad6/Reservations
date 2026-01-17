package com.mycompany.dao;

import com.mycompany.data.ReservationSeries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationSeriesDao {

    private static final ReservationSeriesDao instance = new ReservationSeriesDao();

    private ReservationSeriesDao() {}

    public static ReservationSeriesDao getInstance() {
        return instance;
    }

    // ================== FIND ALL ==================
    public List<ReservationSeries> findAll(Connection con) throws SQLException {

        String sql = "SELECT * FROM reservation_series";
        List<ReservationSeries> list = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ReservationSeries s = new ReservationSeries();
                s.setId(rs.getInt("series_id"));
                s.setType(rs.getString("type"));
                // startDate ne postoji u bazi
                s.setStartDate(null);
                // endDate u klasi je String (ISO)
                s.setEndDate(rs.getDate("end_date").toString());
                list.add(s);
            }
        }
        return list;
    }

    // ================== FIND BY ID ==================
    public ReservationSeries findById(int id, Connection con) throws SQLException {

        String sql = "SELECT * FROM reservation_series WHERE series_id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ReservationSeries s = new ReservationSeries();
                    s.setId(rs.getInt("series_id"));
                    s.setType(rs.getString("type"));
                    s.setStartDate(null);
                    s.setEndDate(rs.getDate("end_date").toString());
                    return s;
                }
            }
        }
        return null;
    }

    // ================== INSERT ==================
    public int insert(ReservationSeries series, Connection con) throws SQLException {

        String sql = "INSERT INTO reservation_series (type, end_date) VALUES (?, ?)";

        try (PreparedStatement ps =
                     con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, series.getType());
            ps.setDate(2, java.sql.Date.valueOf(series.getEndDate())); // endDate je String "YYYY-MM-DD"

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }
}
