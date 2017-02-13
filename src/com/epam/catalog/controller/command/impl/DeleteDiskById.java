package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.List;


public class DeleteDiskById implements com.epam.catalog.controller.command.Command {
    @Override
    public List<?> execute(String request) {
        int id;
        String[] arr = request.split(",");
        if (arr.length == 1) return null;
        for (String element : arr) {
            element.trim();
            System.out.println(element);
        }

        try {
            id = Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            System.out.println("Illegal format for parameter " + e);
            return null;
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DiskService diskService = serviceFactory.getDiskService();
        List<Disk> diskFound = null;
        try {


            diskService.deleteDisk(id);
            diskFound = diskService.getAll();

        } catch (ServiceException e) {
            // write log
            System.out.println(MESSAGE_EXECUTE + e);
        }

        return diskFound;


    }
}
