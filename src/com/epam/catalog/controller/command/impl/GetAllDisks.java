package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.List;


public class GetAllDisks implements com.epam.catalog.controller.command.Command {
    @Override
    public List<?> execute(String request) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DiskService diskService = serviceFactory.getDiskService();
        List<Disk> disksFound = null;
        try {
            disksFound = diskService.getAll();


        } catch (ServiceException e) {

            // write log
            System.out.println("Controller,SearchDiskByPrice:Error during searching procedure");
        }

        return disksFound;
    }
}

