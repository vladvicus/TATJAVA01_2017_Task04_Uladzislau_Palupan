package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.List;


public class UpdateDiskById implements com.epam.catalog.controller.command.Command {
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
Disk disk=new Disk();
        Disk diskForUpdate=disk.makeDisk();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DiskService diskService = serviceFactory.getDiskService();
        List<Disk> diskFoundById = null;
        try {
            diskFoundById = diskService.updateDiskById(id,diskForUpdate);


        } catch (ServiceException e) {

            // write log
            System.out.println("Controller,SearchBookByPrice:Error during searching procedure");
        }

        return diskFoundById;

    }


}

