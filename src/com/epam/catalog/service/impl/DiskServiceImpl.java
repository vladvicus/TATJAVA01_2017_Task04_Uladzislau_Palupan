package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.dao.DiskDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;

import java.util.List;

public class DiskServiceImpl implements DiskService {
private final  String RESPONSE="Error during searching procedure from DiskServiceImpl";
	@Override
	public void addDisk(Disk disk) throws ServiceException {

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			DiskDao diskDao = daoFactory.getDiskDao();

			diskDao.addDisk(disk);

		} catch (DaoException e) {

			throw new ServiceException(RESPONSE);

			// write log
		}
	}

	@Override
	public List<Disk> findDisksByName(String name) throws ServiceException {


		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			DiskDao diskDao = daoFactory.getDiskDao();

			List<Disk> disksFind = diskDao.findDisksByName(name);

			return disksFind;
		} catch (DaoException e) {

			throw new ServiceException(RESPONSE);

			// write log
		}

	}

	@Override
	public List<Disk> findDisksLessThanPrice(Double price) throws ServiceException {



		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			DiskDao diskDao = daoFactory.getDiskDao();

			List<Disk> disksFind = diskDao.findDisksLessThanPrice(price);

			return disksFind;
		} catch (DaoException e) {

			throw new ServiceException(RESPONSE);

		}
	}

}
