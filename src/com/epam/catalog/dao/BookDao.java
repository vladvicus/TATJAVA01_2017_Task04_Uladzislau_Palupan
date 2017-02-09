package com.epam.catalog.dao;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.exception.DaoException;


import java.sql.SQLException;
import java.util.List;


 
public interface BookDao {
	
	


    public Book read(int key) throws SQLException;


    public void update(Book book);


    public void delete(int id);


    public List<Book> getAll() throws SQLException;
    
    void addBook(Book book) throws DaoException;
    
    List<Book> findBooksLessThenPrice(Double price) throws DaoException;
    
    List<Book> findBooksByAuthor(String author) throws DaoException;
    
}
