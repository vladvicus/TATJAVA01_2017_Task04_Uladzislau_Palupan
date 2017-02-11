package com.epam.catalog.dao;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.dao.exception.DaoException;


import java.util.List;

public interface DiskDao {
   List<Disk> updateDiskById(int id) throws DaoException;
	void addDisk(Disk disk) throws DaoException;

	List<Disk> findDisksLessThanPrice(Double price) throws DaoException;

	List<Disk> findDisksByName(String name) throws DaoException;
}
