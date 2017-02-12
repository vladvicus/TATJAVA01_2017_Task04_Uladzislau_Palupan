package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Disk;
import com.epam.catalog.bean.Film;
import com.epam.catalog.dao.DiskDao;
import com.epam.catalog.dao.FilmDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;
import com.epam.catalog.service.FilmService;
import com.epam.catalog.service.exception.ServiceException;

import java.util.List;

public class FilmServiceImpl implements FilmService {
	final 	String RESPONSE="Error during searching procedure from FilmServiceImpl";
	@Override
	public void addFilm(Film film) throws ServiceException {

		DaoFactory daoFactory=DaoFactory.getInstance();
		FilmDao filmDao=daoFactory.getFilmDao();
		try {
			filmDao.addFilm(film);
		} catch (DaoException e) {

			throw new ServiceException(RESPONSE+e);
	
		}
	}

	@Override
	public List<Film> findFilmsByName(String name) throws ServiceException {

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			FilmDao filmDao = daoFactory.getFilmDao();

			List<Film> filmsFind = filmDao.findFilmsByName(name);

			return filmsFind;
		} catch (DaoException e) {

			throw new ServiceException(RESPONSE+e);

			// write log
		}
		
	}

	@Override
	public List<Film> findFilmsGreaterThanRating(Integer rating) throws ServiceException {

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			FilmDao filmDao = daoFactory.getFilmDao();

			List<Film> filmsFind = filmDao.findFilmsGreaterThanRating(rating);

			return filmsFind;
		} catch (DaoException e) {

			throw new ServiceException(RESPONSE+e);

			// write log
		}
		
	}

  

}
