package com.example.aulateste.setor.component;

import com.example.aulateste.setor.controller.SetorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

interface MenuHandlerInterface {
    void handleMenu(SetorController setorController);
}

@Component
public class ConsoleMenuHandler implements MenuHandlerInterface {

    UserInputProvider userInputProvider;

    @Autowired
    public ConsoleMenuHandler(UserInputProvider userInputProvider) {
        this.userInputProvider = userInputProvider;
    }

    @Override
    public void handleMenu(SetorController setorController) {
        boolean isLoop = true;
        while (isLoop) {
            setorController.listarSetores();
            System.out.println("\nMenu:");
            System.out.println("1. Criar Setor");
            System.out.println("2. Editar Setor");
            System.out.println("3. Excluir Setor");
            System.out.println("0. Sair");

            int opcao = Integer.parseInt(userInputProvider.getUserInput());

            switch (opcao) {
                case 1:
                    setorController.criarSetor();
                    break;
                case 2:
                    setorController.editarSetor();
                    break;
                case 3:
                    setorController.excluirSetor();
                    break;
                case 0:
                    userInputProvider.setUserOutput("Saindo...");
                    isLoop = false;
                    break;
                default:
                    userInputProvider.setUserOutput("Opção inválida.");
            }
        }
    }
}
