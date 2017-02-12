package com.epam.catalog.controller;

import com.epam.catalog.controller.command.Command;
import com.epam.catalog.controller.command.CommandName;
import com.epam.catalog.controller.command.impl.*;


import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {

		repository.put(CommandName.SEARCH_BOOK_LESS_THAN_PRICE, new SearchBookLessThanPrice());
		repository.put(CommandName.SEARCH_BOOK_BY_AUTHOR, new SearchBookByAuthor());
		repository.put(CommandName.ADD_BOOK, new AddBook());
		repository.put(CommandName.SEARCH_BOOK_BY_ID,new SearchBookById());
		repository.put(CommandName.GET_ALL_BOOKS, new GetAllBooks());
		repository.put(CommandName.DELETE_BOOK_BY_ID,new DeleteBookById());
		repository.put(CommandName.SEARCH_DISK_LESS_THAN_PRICE, new SearchDiskLessThanPrice());
		repository.put(CommandName.UPDATE_DISK_BY_ID, new UpdateDiskById());
		repository.put(CommandName.SEARCH_DISK_BY_NAME, new SearchDiskByName());
		repository.put(CommandName.DELETE_DISK_BY_ID, new DeleteDiskById());
		repository.put(CommandName.SEARCH_DISK_BY_ID,new SearchDiskById());
		repository.put(CommandName.GET_ALL_DISKS, new GetAllDisks());
		repository.put(CommandName.ADD_DISK, new AddDisk());
		repository.put(CommandName.SEARCH_FILM_BIGGER_THAN_RATING, new SearchFilmBiggerThanRating());
		repository.put(CommandName.SEARCH_FILM_BY_NAME, new SearchFilmByName());
		repository.put(CommandName.ADD_FILM, new AddFilm());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
		
		// ...
	}

	Command getCommand(String name) {
		CommandName commandName = null;
		Command command = null;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			// write log
			command = repository.get(CommandName.WRONG_REQUEST);
		}
		return command;
	}
}
