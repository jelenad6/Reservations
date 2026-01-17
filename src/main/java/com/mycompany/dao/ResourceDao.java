package com.mycompany.dao;

import com.mycompany.data.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResourceDao {

    private static final ResourceDao instance = new ResourceDao();

    private ResourceDao() {}

    public static ResourceDao getInstance() {
        return instance;
    }

    // Find resource by ID
    public Resource findById(int id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Resource resource = null;

        try {
            ps = con.prepareStatement(
                "SELECT * FROM resources WHERE resource_id=?"
            );
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                resource = new Resource(
                    rs.getInt("resource_id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getString("working_hours")
                );
            }
        } finally {
            ResourceManager.closeResources(rs, ps);
        }
        return resource;
    }

    // Find all resources
    public List<Resource> findAll(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Resource> resources = new ArrayList<>();

        try {
            ps = con.prepareStatement("SELECT * FROM resources");
            rs = ps.executeQuery();

            while (rs.next()) {
                Resource resource = new Resource(
                    rs.getInt("resource_id"),
                    rs.getString("name"),
                    rs.getString("type"),
                    rs.getString("working_hours")
                );
                resources.add(resource);
            }
        } finally {
            ResourceManager.closeResources(rs, ps);
        }
        return resources;
    }

    // Insert resource
    public int insert(Resource resource, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;

        try {
            ps = con.prepareStatement(
                "INSERT INTO resources(name, type, working_hours) VALUES (?,?,?)",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, resource.getName());
            ps.setString(2, resource.getType());
            ps.setString(3, resource.getWorkingHours());
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

    // Update resource
    public void update(Resource resource, Connection con) throws SQLException {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(
                "UPDATE resources SET name=?, type=?, working_hours=? WHERE resource_id=?"
            );
            ps.setString(1, resource.getName());
            ps.setString(2, resource.getType());
            ps.setString(3, resource.getWorkingHours());
            ps.setInt(4, resource.getId());
            ps.executeUpdate();
        } finally {
            ResourceManager.closeResources(null, ps);
        }
    }

    // Delete resource
    public void delete(int id, Connection con) throws SQLException {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(
                "DELETE FROM resources WHERE resource_id=?"
            );
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            ResourceManager.closeResources(null, ps);
        }
    }
}
