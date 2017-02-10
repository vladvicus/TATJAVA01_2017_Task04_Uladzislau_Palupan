package com.epam.catalog.service;

import com.epam.catalog.bean.Book;
import com.epam.catalog.service.exception.ServiceException;

import java.util.List;


public interface BookService {


    void addBook(Book book) throws ServiceException;

    List<Book> getAll() throws ServiceException;

    List<Book> readBook(int id) throws ServiceException;


    List<Book> findBooksLessThenPrice(Double price) throws ServiceException;

    List<Book> findBooksByAuthor(String author) throws ServiceException;

    void deleteBook(int id) throws ServiceException;
}