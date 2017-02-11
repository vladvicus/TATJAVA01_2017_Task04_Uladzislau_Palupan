package com.epam.catalog.controller.command.impl;

import java.util.ArrayList;
import java.util.List;


import com.epam.catalog.bean.Disk;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.DiskService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

public class AddDisk implements Command {

    @Override
    public List<?> execute(String request) {


        System.out.println(request);

        request = request.replaceAll("\\s{2,}", " ");


        Disk disk = new Disk();
        Disk newDisk = disk.makeDisk();
        List<Disk> addedDisk = new ArrayList<Disk>();
        addedDisk.add(newDisk);


        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DiskService diskService = serviceFactory.getDiskService();
        try {
            diskService.addDisk(newDisk);

        } catch (ServiceException e) {
            System.out.println(e);

        }

        return addedDisk;

    }

}
