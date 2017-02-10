package com.epam.catalog.dao;

import com.epam.catalog.bean.Book;
import com.epam.catalog.dao.exception.DaoException;


import java.sql.SQLException;
import java.util.List;


 
public interface BookDao {
	
	


    public List<Book> read(int key) throws DaoException;





    public void delete(int id);


    public List<Book> getAll() throws DaoException;
    
    void addBook(Book book) throws DaoException;
    
    List<Book> findBooksLessThenPrice(Double price) throws DaoException;
    
    List<Book> findBooksByAuthor(String author) throws DaoException;
    
}
