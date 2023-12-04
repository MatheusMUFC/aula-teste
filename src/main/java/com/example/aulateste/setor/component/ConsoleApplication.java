package com.example.aulateste.setor.component;

import com.example.aulateste.setor.controller.SetorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleApplication implements CommandLineRunner {

    private final SetorController setorController;
    private final MenuHandlerInterface menuHandler;

    public ConsoleApplication(SetorController setorController, MenuHandlerInterface menuHandler) {
        this.setorController = setorController;
        this.menuHandler = menuHandler;
    }

    @Override
    public void run(String... args) throws Exception {
        menuHandler.handleMenu(setorController);
    }
}

