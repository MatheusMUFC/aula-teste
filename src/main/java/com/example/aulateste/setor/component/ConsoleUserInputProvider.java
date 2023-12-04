package com.example.aulateste.setor.component;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleUserInputProvider implements UserInputProvider {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getUserInput() {
        return scanner.nextLine();
    }

    @Override
    public void setUserOutput(String text) {
        System.out.println(text);
    }

}
