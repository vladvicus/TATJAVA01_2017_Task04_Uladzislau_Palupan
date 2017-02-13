package com.epam.catalog.controller.command.impl;


import com.epam.catalog.bean.Book;
import com.epam.catalog.controller.command.Command;
import com.epam.catalog.service.BookService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

import java.util.List;

public class DeleteBookById implements Command {
    @Override
    public List<?> execute(String request) {
        int id;
        String[] arr = request.split(",");
        if (arr.length == 1) return null;
        for (String element : arr) {
            element.trim();
            System.out.println(element);
        }

        try {
            id = Integer.parseInt(arr[1]);
        } catch (NumberFormatException e) {
            System.out.println("Illegal format for parameter " + e);
            return null;
        }

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();
        List<Book> bookFound = null;
        try {
            bookService.deleteBook(id);
            bookFound = bookService.getAll();
        } catch (ServiceException e) {
            // write log
            System.out.println(MESSAGE_EXECUTE + e);
        }
        return bookFound;

    }


}

