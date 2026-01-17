package com.mycompany.dao;

import com.mycompany.data.Slot;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SlotDao {

    private static final SlotDao instance = new SlotDao();

    private SlotDao() {}

    public static SlotDao getInstance() {
        return instance;
    }

    // Pronađi zauzete slotove za resurs i datum
    public List<Integer> findReservedSlotIds(int resourceId, LocalDate date, Connection con)
            throws SQLException {

        List<Integer> reserved = new ArrayList<>();

        String sql =
            "SELECT s.slot_id " +
            "FROM slots s " +
            "JOIN reservations r ON s.slot_id = r.slot_id " +
            "WHERE s.resource_id = ? AND s.date = ? AND r.status = 'ACTIVE'";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, resourceId);
            ps.setDate(2, Date.valueOf(date));

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    reserved.add(rs.getInt(1));
                }
            }
        }
        return reserved;
    }

    // ✅ NOVO: Nadji slot po ID
    public Slot findById(int slotId, Connection con) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement("SELECT * FROM slots WHERE slot_id=?");
            ps.setInt(1, slotId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Slot(
                    rs.getInt("slot_id"),
                    rs.getInt("resource_id"),
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("time").toLocalTime()
                );
            }
            return null;

        } finally {
            ResourceManager.closeResources(rs, ps);
        }
    }

    // ✅ NOVO: Nadji slot po resourceId + date + time
    public Slot findByResourceDateTime(int resourceId,
                                       LocalDate date,
                                       LocalTime time,
                                       Connection con) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(
                "SELECT * FROM slots WHERE resource_id=? AND date=? AND time=?"
            );
            ps.setInt(1, resourceId);
            ps.setDate(2, Date.valueOf(date));
            ps.setTime(3, Time.valueOf(time));

            rs = ps.executeQuery();

            if (rs.next()) {
                return new Slot(
                    rs.getInt("slot_id"),
                    rs.getInt("resource_id"),
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("time").toLocalTime()
                );
            }
            return null;

        } finally {
            ResourceManager.closeResources(rs, ps);
        }
    }

    // Pronađi slotove po resursu i datumu
    public List<Slot> findByResourceAndDate(int resourceId, LocalDate date, Connection con) throws SQLException {
        List<Slot> slots = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(
                "SELECT * FROM slots WHERE resource_id=? AND date=? ORDER BY time"
            );
            ps.setInt(1, resourceId);
            ps.setDate(2, Date.valueOf(date));
            rs = ps.executeQuery();

            while (rs.next()) {
                slots.add(new Slot(
                    rs.getInt("slot_id"),
                    rs.getInt("resource_id"),
                    rs.getDate("date").toLocalDate(),
                    rs.getTime("time").toLocalTime()
                ));
            }
        } finally {
            ResourceManager.closeResources(rs, ps);
        }

        return slots;
    }

    // Insert novog slota
    public int insert(Slot slot, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;

        try {
            ps = con.prepareStatement(
                "INSERT INTO slots(resource_id, date, time) VALUES (?,?,?)",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1, slot.getResourceId());
            ps.setDate(2, Date.valueOf(slot.getDate()));
            ps.setTime(3, Time.valueOf(slot.getTime()));
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } finally {
            ResourceManager.closeResources(rs, ps);
        }

        return id;
    }
}
