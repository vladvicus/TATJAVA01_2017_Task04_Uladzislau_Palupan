package com.epam.catalog.dao.impl;

import com.epam.catalog.bean.Film;
import com.epam.catalog.bean.Film;

import com.epam.catalog.dao.FilmDao;
import com.epam.catalog.dao.connectionpool.ConnectionPool;
import com.epam.catalog.dao.exception.DaoException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilmDaoImpl implements FilmDao {

	public final String MESSAGE = "Error in FilmDaoIMPL!!";
	public final int NUMBER_OF_CONNECTIONS = 3;
	public static ConnectionPool pool;
	private Connection connection = null;

	public FilmDaoImpl() {
	this.pool=ConnectionPool.getInstance(NUMBER_OF_CONNECTIONS);
	}

	@Override
	public void addFilm(Film film) throws DaoException {
			String sql = "INSERT INTO catalog.films (`name`,`country`, `year`, `rating`) VALUES (?,?,?,?)";
			PreparedStatement ps = null;
			try {
				connection = pool.getConnection();
				ps = connection.prepareStatement(sql);
				ps.setString(1, film.getName());
				ps.setString(2, film.getCountry());
				ps.setInt(3, film.getYear());
				ps.setDouble(4, film.getRating());
				ps.executeUpdate();
				System.out.println("Insert is successful!!");
			} catch (SQLException e) {

				throw new DaoException(MESSAGE + e);

			} finally {

				closePrepareStatement(ps);
				pool.freeConnection(connection);
			}

	}
	public void closePrepareStatement(PreparedStatement ps) throws DaoException {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				throw new DaoException("Error while closing PrepareStatement "+e);
			}
		}
	}
	@Override

	public List<Film> findFilmsByName(String name) throws DaoException {

		//SELECT * FROM courses WHERE LOCATE('php', description);
		//final String SQL = "SELECT * FROM catalog.films WHERE name=?";
		final String SQL = "SELECT * FROM catalog.films WHERE (LOCATE(LOWER(?),LOWER(`name`))>0)";
		List<Film> filmList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			ps = connection.prepareStatement(SQL);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setName(rs.getString("name"));
				film.setCountry(rs.getString("country"));
				film.setYear(rs.getInt("year"));
				film.setRating(rs.getInt("rating"));
				filmList.add(film);
			}

		} catch (SQLException e) {
			throw new DaoException(MESSAGE + e);
		} finally {
			closePrepareStatement(ps);
			pool.freeConnection(connection);
		}
		return filmList;
	}

	@Override
	public List<Film> findFilmsGreaterThanRating(Integer rating) throws DaoException {

		final String SQL = "SELECT * FROM catalog.films WHERE `rating` > ? ORDER BY rating ASC";
		List<Film> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = pool.getConnection();
			ps = connection.prepareStatement(SQL);
			ps.setDouble(1, rating);
			rs = ps.executeQuery();
			while (rs.next()) {


				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setName(rs.getString("name"));
				film.setCountry(rs.getString("country"));
				film.setYear(rs.getInt("year"));
				film.setRating(rs.getInt("rating"));

				list.add(film);

			}
		} catch (SQLException e) {

			throw new DaoException("Error while finding films less greater than rating" + e);

		} finally {

			closePrepareStatement(ps);
			pool.freeConnection(connection);
		}
		return list;
	}

	public List<Film> readFile(String fname) throws IOException {
		List<Film> films = new ArrayList<>();
		FileInputStream fis = new FileInputStream(fname);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		String line;
		while ((line = br.readLine()) != null) {
			String[] data = line.split(",");
			for (int i = 0; i < data.length; i++) {
				data[i] = data[i].trim();

			}
			if (data[0].startsWith("film")) {

				String name = data[1];
				String country = data[2];
				Integer year = Integer.parseInt(data[3]);
				Integer rating = Integer.parseInt(data[4]);
				try {
					addFilm(new Film(name, country, year, rating));
				} catch (DaoException e) {
					e.printStackTrace();
				}

			} else
				continue;

		}
		br.close();

		System.out.println("Films are suscessfully loaded from file!");
		return films;
	}
}
