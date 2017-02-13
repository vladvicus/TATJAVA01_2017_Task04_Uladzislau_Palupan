package com.epam.catalog.controller.command.impl;

import java.util.List;

import com.epam.catalog.bean.Film;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.FilmService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

public class SearchFilmByName implements Command {

    @Override
    public List<?> execute(String request) {
        String name;

        request = request.replaceAll("\\s{2,}", " ");
        String[] arr = request.split(",");
        if (arr.length == 1) {
            return null;
        }
        for (String element : arr) {
            element.trim();
        }
        try {
            name = arr[1];
        } catch (NumberFormatException e) {
            System.out.println("Illegal format for parameter name" + e);
            return null;
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();
        List<Film> filmsFoundByName = null;
        try {
            filmsFoundByName = filmService.findFilmsByName(name);

        } catch (ServiceException e) {

            // write log
            System.out.println(MESSAGE_EXECUTE + e);
        }

        return filmsFoundByName;
    }

}
