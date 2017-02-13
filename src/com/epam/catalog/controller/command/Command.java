package com.epam.catalog.controller.command;

import java.util.List;

public interface Command {
    public final String MESSAGE_EXECUTE = "Exception in execute method ";

    public List<?> execute(String request);
}
