package com.mycompany.service;

import com.mycompany.dao.ResourceDao;
import com.mycompany.dao.ResourceManager;
import com.mycompany.data.Resource;
import com.mycompany.exception.ReservationException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ResourceService {

    private static final ResourceService instance = new ResourceService();
    private ResourceDao resourceDao = ResourceDao.getInstance();

    private ResourceService() {}

    public static ResourceService getInstance() {
        return instance;
    }

    // Pregled svih resursa
    public List<Resource> getAllResources() throws ReservationException {
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            return resourceDao.findAll(con);
        } catch (SQLException ex) {
            throw new ReservationException("Failed to fetch resources.", ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }

    // Pregled resursa po ID
    public Resource getResourceById(int id) throws ReservationException {
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            return resourceDao.findById(id, con);
        } catch (SQLException ex) {
            throw new ReservationException("Failed to fetch resource.", ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }

    //Dodavanje resursa
    public int addResource(Resource resource) throws ReservationException {
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            return resourceDao.insert(resource, con);
        } catch (SQLException ex) {
            throw new ReservationException("Failed to add resource.", ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }

    // Izmena resursa
    public void updateResource(Resource resource) throws ReservationException {
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            resourceDao.update(resource, con);
        } catch (SQLException ex) {
            throw new ReservationException("Failed to update resource.", ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }

    // Brisanje resursa
    public void deleteResource(int id) throws ReservationException {
        Connection con = null;
        try {
            con = ResourceManager.getConnection();
            resourceDao.delete(id, con);
        } catch (SQLException ex) {
            throw new ReservationException("Failed to delete resource.", ex);
        } finally {
            ResourceManager.closeConnection(con);
        }
    }
}
