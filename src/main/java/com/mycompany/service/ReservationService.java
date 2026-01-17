package com.mycompany.service;

import com.mycompany.dao.ReservationDao;
import com.mycompany.data.Reservation;
import com.mycompany.dao.ResourceManager;
import com.mycompany.exception.ReservationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReservationService {

    private static final ReservationService instance = new ReservationService();
    private final ReservationDao reservationDao = ReservationDao.getInstance();

    private ReservationService() {}

    public static ReservationService getInstance() {
        return instance;
    }

    public List<Reservation> getAllReservations() throws ReservationException {
        try (Connection con = ResourceManager.getConnection()) {
            return reservationDao.findAll(con);
        } catch (SQLException ex) {
            throw new ReservationException("Failed to fetch reservations", ex);
        }
    }

    public Reservation getReservationById(int id) throws ReservationException {
        try (Connection con = ResourceManager.getConnection()) {
            return reservationDao.findById(id, con);
        } catch (SQLException ex) {
            throw new ReservationException("Failed to fetch reservation", ex);
        }
    }

   public int createReservation(Reservation reservation) throws ReservationException {
    try (Connection con = ResourceManager.getConnection()) {

        
        boolean available =
                reservationDao.isSlotAvailable(reservation.getSlotId(), con);

        if (!available) {
            throw new ReservationException("Slot is already reserved");
        }

      
        return reservationDao.insert(reservation, con);

    } catch (SQLException ex) {
        throw new ReservationException("Failed to create reservation", ex);
    }
}


   public void updateReservation(Reservation reservation) throws ReservationException {
    try (Connection con = ResourceManager.getConnection()) {

        
        Reservation existing =
                reservationDao.findById(reservation.getId(), con);

        if (existing == null) {
            throw new ReservationException("Reservation not found");
        }

       
        if (existing.getSlotId() != reservation.getSlotId()) {

            boolean available =
                    reservationDao.isSlotAvailable(reservation.getSlotId(), con);

            if (!available) {
                throw new ReservationException("Slot is already reserved");
            }
        }

      
        reservationDao.update(reservation, con);

    } catch (SQLException ex) {
        throw new ReservationException("Failed to update reservation", ex);
    }
}


    public void deleteReservation(int id) throws ReservationException {
        try (Connection con = ResourceManager.getConnection()) {
            reservationDao.delete(id, con);
        } catch (SQLException ex) {
            throw new ReservationException("Failed to delete reservation", ex);
        }
    }

    public List<Reservation> getReservationsByUserId(int userId) throws ReservationException {
        try (Connection con = ResourceManager.getConnection()) {
            return reservationDao.findByUserId(userId, con);
        } catch (SQLException ex) {
            throw new ReservationException("Failed to fetch reservations for user", ex);
        }
    }
}
