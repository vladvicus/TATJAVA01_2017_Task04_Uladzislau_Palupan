package com.epam.catalog.dao;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.exception.DaoException;

import java.util.List;


public interface BookDao {

    List<Book> read(int key) throws DaoException;

    void delete(int id) throws DaoException;

    List<Book> getAll() throws DaoException;

    void addBook(Book book) throws DaoException;

    List<Book> findBooksLessThenPrice(Double price) throws DaoException;

    List<Book> findBooksByAuthor(String author) throws DaoException;

}
