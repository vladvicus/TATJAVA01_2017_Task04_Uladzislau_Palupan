package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.List;

/**
 * Created by Uladzislau_Palupan on 2/10/2017.
 */
public class GetAllBooks implements Command {

    @Override
    public List<?> execute(String request) {




        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();
        List<Book> booksFound=null;
        try {
            booksFound=bookService.getAll();


        } catch (ServiceException e) {

            // write log
            System.out.println("Controller,SearchBookByPrice:Error during searching procedure");
        }

        return booksFound;
    }
}
