package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.List;


public class GetAllDisks implements Command {
    @Override
    public List<?> execute(String request) {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DiskService diskService = serviceFactory.getDiskService();
        List<Disk> disksFound = null;
        try {
            disksFound = diskService.getAll();

        } catch (ServiceException e) {
            // write log
            System.out.println(MESSAGE_EXECUTE + e);
        }
        return disksFound;
    }
}

