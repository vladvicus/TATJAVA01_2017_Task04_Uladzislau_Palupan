package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.BookDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.exception.ServiceException;
import java.util.List;

public class BookServiceImpl implements BookService {

	@Override
	public void addBook(Book book) throws ServiceException {

		String response = null;
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			BookDao bookDao = daoFactory.getBookDao();

			bookDao.addBook(book);

		} catch (DaoException e) {
			response = "Error during searching procedure from BookServiceImpl";
			throw new ServiceException(response+e);

			// write log
		}
	}

	@Override
	public List<Book> getAll() throws ServiceException {
		String response = null;

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			BookDao bookDao = daoFactory.getBookDao();

			List<Book> booksFind=bookDao.getAll();
			return booksFind;
		} catch (DaoException e) {
			response = "Error during searching procedure from BookServiceImpl";
			throw new ServiceException(response+e);

		}

	}
	@Override
	public  List<Book> readBook(int id) throws ServiceException {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			BookDao bookDao = daoFactory.getBookDao();

			List<Book> booksFind = bookDao.read(id);

			return booksFind;
		} catch (DaoException e) {

			throw new ServiceException("Error during searching procedure from BookServiceImpl"+e);

		}

	}

	@Override
	public List<Book> findBooksLessThenPrice(Double price) throws ServiceException {
		String response = null;

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			BookDao bookDao = daoFactory.getBookDao();

			List<Book> booksFind = bookDao.findBooksLessThenPrice(price);

			return booksFind;
		} catch (DaoException e) {
			response = "Error during searching procedure from BookServiceImpl";
			throw new ServiceException(response+e);

		}
	}

	@Override
	public List<Book> findBooksByAuthor(String author) throws ServiceException {

		String response = null;
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			BookDao bookDao = daoFactory.getBookDao();

			List<Book> booksFind = bookDao.findBooksByAuthor(author);

			return booksFind;
		} catch (DaoException e) {
			response = "Error during searching procedure from BookServiceImpl";
			throw new ServiceException(response+e);

			// write log
		}

	}

	@Override
	public  void deleteBook(int id) throws ServiceException {

		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			BookDao bookDao = daoFactory.getBookDao();

			 bookDao.delete(id);


		} catch (DaoException e) {

			throw new ServiceException("Error during searching procedure from BookServiceImpl"+e);

		}
	}
}
