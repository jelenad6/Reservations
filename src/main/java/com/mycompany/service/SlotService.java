package com.mycompany.service;

import com.mycompany.dao.SlotDao;
import com.mycompany.dao.ResourceManager;
import com.mycompany.data.Resource;
import com.mycompany.data.Slot;
import com.mycompany.exception.ReservationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SlotService {

    private static final SlotService instance = new SlotService();
    private SlotDao slotDao = SlotDao.getInstance();
    private ResourceService resourceService = ResourceService.getInstance();

    private SlotService() {}

    public static SlotService getInstance() {
        return instance;
    }

    
    public List<Slot> getSlotsByResourceAndDate(int resourceId, LocalDate date)
        throws ReservationException {

    try (Connection con = ResourceManager.getConnection()) {

        
        List<Slot> allSlots = slotDao.findByResourceAndDate(resourceId, date, con);

        if (allSlots.isEmpty()) {
            Resource resource = resourceService.getResourceById(resourceId);
            if (resource == null) {
                throw new ReservationException("Resource not found");
            }

            String[] parts = resource.getWorkingHours().split("-");
            int startHour = Integer.parseInt(parts[0].split(":")[0]);
            int endHour = Integer.parseInt(parts[1].split(":")[0]);

            for (int h = startHour; h < endHour; h++) {
                Slot slot = new Slot(resourceId, date, LocalTime.of(h, 0));
                int id = slotDao.insert(slot, con);
                slot.setId(id);
                allSlots.add(slot);
            }
        }

        
        List<Integer> reservedIds =
                slotDao.findReservedSlotIds(resourceId, date, con);

        
        List<Slot> free = new ArrayList<>();
        for (Slot s : allSlots) {
            if (!reservedIds.contains(s.getId())) {
                free.add(s);
            }
        }

        return free;

    } catch (SQLException ex) {
        throw new ReservationException("Failed to fetch slots", ex);
    }
}

}
