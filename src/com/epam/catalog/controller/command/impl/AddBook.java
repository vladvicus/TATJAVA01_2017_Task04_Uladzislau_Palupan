package com.epam.catalog.controller.command.impl;

import com.epam.catalog.bean.Book;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

public class AddBook implements Command {

    @Override
    public List<?> execute(String request) {

        System.out.println(request);

        request = request.replaceAll("\\s{2,}", " ");


        Book book = new Book();
        Book newBook = book.makeBook();
        List<Book> addedBook = new ArrayList<Book>();
        addedBook.add(newBook);


        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();
        try {
            bookService.addBook(newBook);

        } catch (ServiceException e) {
            System.out.println(e);
        }

        return addedBook;

    }

}
