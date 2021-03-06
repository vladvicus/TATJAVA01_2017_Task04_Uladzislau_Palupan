package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Film;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.FilmService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.List;

public class SearchFilmBiggerThanRating implements Command {

    @Override
    public List<?> execute(String request) {
        int rating;

        request = request.replaceAll("\\s{2,}", " ");
        String[] arr = request.split(",");
        if (arr.length == 1) {
            return null;
        }
        for (String element : arr) {
            element.trim();
            System.out.println(element);
        }
        try {
            rating = Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            System.out.println("Illegal format for parameter rating" + e);
            return null;
        }
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();
        List<Film> filmsFoundByPrice = null;
        try {
            filmsFoundByPrice = filmService.findFilmsGreaterThanRating(rating);

        } catch (ServiceException e) {
            // write log
            System.out.println(MESSAGE_EXECUTE + e);
        }
        return filmsFoundByPrice;
    }

}
