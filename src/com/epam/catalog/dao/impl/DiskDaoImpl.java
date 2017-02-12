package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.Disk;

import com.epam.catalog.dao.DiskDao;
import com.epam.catalog.dao.connectionpool.ConnectionPool;
import com.epam.catalog.dao.exception.DaoException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DiskDaoImpl implements DiskDao {
    public final String MESSAGE = "Error in DiskDaoIMPL!!";
    public final int NUMBER_OF_CONNECTIONS = 3;
    public static ConnectionPool pool;
    private Connection connection = null;

    public DiskDaoImpl() {
        this.pool = ConnectionPool.getInstance(NUMBER_OF_CONNECTIONS);
    }

    @Override
    public List<Disk> updateDiskById(int id,Disk diskForUpdate) throws DaoException {
        final String SQL="UPDATE catalog.disks SET type = ?, name = ?,year= ?,price= ?  WHERE id = ?";
        List<Disk> list = new ArrayList<>();
        PreparedStatement ps=null;
        try{
            connection=pool.getConnection();
            ps=connection.prepareStatement(SQL);
            ps.setString(1,diskForUpdate.getType());
            ps.setString(2,diskForUpdate.getName());
            ps.setInt(3,diskForUpdate.getYear());
            ps.setDouble(4,diskForUpdate.getPrice());
            ps.setInt(5,id);
            ps.executeUpdate();

            diskForUpdate.setId(id);

           list.add(diskForUpdate);
        }catch(SQLException e){
            throw new DaoException(MESSAGE +e);
        }
        return list;
    }

    @Override
    public void addDisk(Disk disk) throws DaoException {
        String sql = "INSERT INTO catalog.disks (`type`, `name`, `year`, `price`) VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, disk.getType());
            ps.setString(2, disk.getName());
            ps.setInt(3, disk.getYear());
            ps.setDouble(4, disk.getPrice());
            ps.executeUpdate();
            System.out.println("Insert is successful!!");
        } catch (SQLException e) {

            throw new DaoException(MESSAGE + e);

        } finally {

            closePrepareStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Disk> findDisksLessThanPrice(Double price) throws DaoException {
        final String SQL = "SELECT * FROM catalog.disks WHERE price < ?";
        List<Disk> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(SQL);
            ps.setDouble(1, price);
            rs = ps.executeQuery();
            while (rs.next()) {


                Disk disk = new Disk();
                disk.setId(rs.getInt("id"));
                disk.setType(rs.getString("type"));
                disk.setName(rs.getString("name"));
                disk.setYear(rs.getInt("year"));
                disk.setPrice(rs.getDouble("price"));

                list.add(disk);

            }
        } catch (SQLException e) {

            throw new DaoException("Error while finding disks less than price" + e);

        } finally {

            closePrepareStatement(ps);
            pool.freeConnection(connection);
        }
        return list;


    }

    //UPDATE Messages SET description = ?, author = ? WHERE id = ? AND seq_num = ?
    @Override
    public List<Disk> findDisksByName(String name) throws DaoException {
        final String SQL = "SELECT * FROM catalog.disks WHERE name=?";
        List<Disk> diskList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(SQL);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                Disk disk = new Disk();
                disk.setId(rs.getInt("id"));
                disk.setType(rs.getString("type"));
                disk.setName(rs.getString("name"));
                disk.setYear(rs.getInt("year"));
                disk.setPrice(rs.getDouble("price"));
                diskList.add(disk);
            }

        } catch (SQLException e) {
            throw new DaoException(MESSAGE + e);
        } finally {
            closePrepareStatement(ps);
            pool.freeConnection(connection);
        }
        return diskList;
    }

    @Override
    public void delete(int id) throws DaoException {

        String sql = "DELETE FROM catalog.disks WHERE id=?";
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Deletion successful!!");
        } catch (SQLException e) {
            System.out.println("Error while deleting by id" + e);
            e.printStackTrace();
        } finally {
            closePrepareStatement(ps);
            pool.freeConnection(connection);
        }
    }

    @Override
    public List<Disk> getAll() throws DaoException {
        String sql = "SELECT * FROM catalog.disks;";
        List<Disk> list = new ArrayList<Disk>();
        PreparedStatement ps = null;
        try {
            connection = pool.getConnection();
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Disk disk = new Disk();
                disk.setId(rs.getInt("id"));
                disk.setType(rs.getString("type"));
                disk.setName(rs.getString("name"));
                disk.setYear(rs.getInt("year"));
                disk.setPrice(rs.getDouble("price"));

                list.add(disk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            closePrepareStatement(ps);
            pool.freeConnection(connection);
        }
        return list;
    }

    public List<Disk> readFile(String fname) throws IOException{
        List<Disk> disks = new ArrayList<>();
        FileInputStream fis = new FileInputStream(fname);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            for (int i = 0; i < data.length; i++) {
                data[i] = data[i].trim();

            }
            if (data[0].startsWith("disk")) {

                String type = data[1];
                String name = data[2];
                Integer year = Integer.parseInt(data[3]);
                Double price = Double.parseDouble(data[4]);
                try {
                    addDisk(new Disk(type, name, year, price));
                } catch (DaoException e) {
                    e.printStackTrace();
                }


            } else
                continue;

        }
        br.close();
        System.out.println("Disks are suscessfully loaded from file!");


        return disks;
    }

}
