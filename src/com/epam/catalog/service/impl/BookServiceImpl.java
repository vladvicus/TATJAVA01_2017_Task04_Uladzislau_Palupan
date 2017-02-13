package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.BookDao;
import com.epam.catalog.dao.exception.DaoException;
import com.epam.catalog.dao.factory.DaoFactory;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.exception.ServiceException;

import java.util.List;

public class BookServiceImpl implements BookService {
    public final String MESSAGE = "Error during searching procedure from BookServiceImpl";

    @Override
    public void addBook(Book book) throws ServiceException {


        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            BookDao bookDao = daoFactory.getBookDao();

            bookDao.addBook(book);

        } catch (DaoException e) {

            throw new ServiceException(MESSAGE + e);

            // write log
        }
    }

    @Override
    public List<Book> getAll() throws ServiceException {


        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            BookDao bookDao = daoFactory.getBookDao();

            List<Book> booksFind = bookDao.getAll();
            return booksFind;
        } catch (DaoException e) {

            throw new ServiceException(MESSAGE + e);

        }

    }

    @Override
    public List<Book> readBook(int id) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            BookDao bookDao = daoFactory.getBookDao();

            List<Book> booksFind = bookDao.read(id);

            return booksFind;
        } catch (DaoException e) {

            throw new ServiceException(MESSAGE + e);

        }

    }

    @Override
    public List<Book> findBooksLessThenPrice(Double price) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            BookDao bookDao = daoFactory.getBookDao();

            List<Book> booksFind = bookDao.findBooksLessThenPrice(price);

            return booksFind;
        } catch (DaoException e) {

            throw new ServiceException(MESSAGE + e);

        }
    }

    @Override
    public List<Book> findBooksByAuthor(String author) throws ServiceException {


        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            BookDao bookDao = daoFactory.getBookDao();

            List<Book> booksFind = bookDao.findBooksByAuthor(author);

            return booksFind;
        } catch (DaoException e) {

            throw new ServiceException(MESSAGE + e);

            // write log
        }

    }

    @Override
    public void deleteBook(int id) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            BookDao bookDao = daoFactory.getBookDao();

            bookDao.delete(id);


        } catch (DaoException e) {

            throw new ServiceException(MESSAGE + e);

        }
    }
}
