package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Film;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.FilmService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class AddFilm implements Command {

    @Override
    public List<?> execute(String request) {

        request = request.replaceAll("\\s{2,}", " ");

        Film film = new Film();
        Film newFilm = film.makeFilm();
        List<Film> addedFilm = new ArrayList<Film>();
        addedFilm.add(newFilm);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FilmService filmService = serviceFactory.getFilmService();
        try {
            filmService.addFilm(newFilm);

        } catch (ServiceException e) {

            System.out.println(MESSAGE_EXECUTE + e);
        }

        return addedFilm;

    }

}
