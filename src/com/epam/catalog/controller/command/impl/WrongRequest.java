package com.epam.catalog.controller.command.impl;


import com.epam.catalog.controller.command.Command;
import com.epam.catalog.controller.command.CommandName;

import java.util.List;

public class WrongRequest implements Command {

    @Override
    public List<?> execute(String request) {
        System.out.println("Your command  is wrong!!!");
        System.out.println("Available commands:");
        for (CommandName name : CommandName.values()) {
            if (name.equals(CommandName.WRONG_REQUEST))
                continue;
            System.out.println(name + ",parameter");
        }
        return null;
    }

}
