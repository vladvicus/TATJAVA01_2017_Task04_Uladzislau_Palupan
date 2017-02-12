package com.epam.catalog.service;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.service.exception.ServiceException;

import java.util.List;


public interface DiskService {

    void addDisk(Disk disk) throws ServiceException;

     List<Disk> findDisksLessThanPrice(Double price) throws ServiceException;

    List<Disk> findDisksByName(String name) throws ServiceException;

    List<Disk> updateDiskById(int id,Disk diskForUpdate) throws ServiceException;

    void deleteDisk(int id)throws ServiceException ;

    List<Disk> getAll()throws ServiceException ;

    List<Disk> readDisk(int id) throws ServiceException ;
}
