package com.mycompany.service;

import com.mycompany.dao.ReservationDao;
import com.mycompany.dao.ReservationSeriesDao;
import com.mycompany.dao.ResourceManager;
import com.mycompany.dao.SlotDao;
import com.mycompany.data.Reservation;
import com.mycompany.data.ReservationSeries;
import com.mycompany.data.Slot;
import com.mycompany.exception.ReservationException;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationSeriesService {

    private static final ReservationSeriesService instance = new ReservationSeriesService();

    private final ReservationDao reservationDao = ReservationDao.getInstance();
    private final ReservationSeriesDao seriesDao = ReservationSeriesDao.getInstance();
    private final SlotDao slotDao = SlotDao.getInstance();

    private ReservationSeriesService() {}

    public static ReservationSeriesService getInstance() {
        return instance;
    }

    public int createSeriesAndReservations(ReservationSeries series) throws ReservationException {

        Connection con = null;

        try {
            // 0) validacije pre parsiranja
            if (series == null) {
                throw new ReservationException("Request body is missing");
            }
            if (series.getType() == null || series.getStartDate() == null || series.getEndDate() == null) {
                throw new ReservationException("Missing type/startDate/endDate");
            }

            // 1) parsiraj String -> LocalDate
            LocalDate start;
            LocalDate end;
            try {
                start = LocalDate.parse(series.getStartDate()); // "YYYY-MM-DD"
                end = LocalDate.parse(series.getEndDate());
            } catch (Exception e) {
                throw new ReservationException("Invalid date format. Expected YYYY-MM-DD");
            }

            if (start.isAfter(end)) {
                throw new ReservationException("startDate must be <= endDate");
            }

            con = ResourceManager.getConnection();
            con.setAutoCommit(false);

            // 2) ubaci series (tabela ima: type + end_date)
            int seriesId = seriesDao.insert(series, con);
            if (seriesId <= 0) {
                throw new ReservationException("Series insert failed");
            }

            // 3) base slot mora postojati (ovo je template: resurs + vreme)
            Slot base = slotDao.findById(series.getSlotId(), con);
            if (base == null) {
                throw new ReservationException("Base slot not found (slotId=" + series.getSlotId() + ")");
            }

            int resourceId = base.getResourceId();
            LocalTime time = base.getTime();

            // 4) prolazak kroz datume
            LocalDate current = start;

            while (!current.isAfter(end)) {

                // pronadji slot za taj datum + isto vreme + isti resurs
                Slot slot = slotDao.findByResourceDateTime(resourceId, current, time, con);

                if (slot == null) {
                    // ako ne postoji, kreiraj ga
                    Slot newSlot = new Slot(resourceId, current, time);
                    int newId = slotDao.insert(newSlot, con);
                    newSlot.setId(newId);
                    slot = newSlot;
                }

                // provera zauzetosti (single reservation logika)
                boolean available = reservationDao.isSlotAvailable(slot.getId(), con);
                if (!available) {
                    throw new ReservationException("Slot already reserved for " + current + " " + time);
                }

                // insert reservation vezan za seriesId
                Reservation r = new Reservation();
                r.setUserId(series.getUserId());
                r.setSlotId(slot.getId());
                r.setStatus("ACTIVE");
                r.setSeriesId(seriesId);

                reservationDao.insert(r, con);

                // sledeći datum
                String type = series.getType();
                if (type == null) {
                    throw new ReservationException("Invalid series type");
                }

                switch (type) {
                    case "daily":
                        current = current.plusDays(1);
                        break;
                    case "weekly":
                        current = current.plusWeeks(1);
                        break;
                    case "monthly":
                        current = current.plusMonths(1);
                        break;
                    default:
                        throw new ReservationException("Invalid series type: " + type);
                }
            }

            con.commit();
            return seriesId;

        } catch (ReservationException ex) {
            try { if (con != null) con.rollback(); } catch (Exception ignore) {}
            throw ex;
        } catch (Exception ex) {
            try { if (con != null) con.rollback(); } catch (Exception ignore) {}
            throw new ReservationException("Failed to create reservation series: " + ex.getMessage(), ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }
}
