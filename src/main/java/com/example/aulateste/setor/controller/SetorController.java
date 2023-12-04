package com.example.aulateste.setor.controller;

import com.example.aulateste.setor.component.UserInputProvider;
import com.example.aulateste.setor.entity.Setor;
import com.example.aulateste.setor.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class SetorController {
    private final SetorService setorService;
    private final UserInputProvider userInputProvider;

    @Autowired
    public SetorController(SetorService setorService, UserInputProvider userInputProvider) {
        this.setorService = setorService;
        this.userInputProvider = userInputProvider;
    }

    private void printDivisor() {
        userInputProvider.setUserOutput("------------------------------------------------");
    }

    private void printSetor(Setor setor) {
        userInputProvider.setUserOutput(setor.getId() + ". " + setor.getNome());
    }

    public List<Setor> listarSetores() {
        printDivisor();
        userInputProvider.setUserOutput("Lista dos setores:\n");
        List<Setor> setores = setorService.listarSetores();
        for (Setor setor : setores) {
            printSetor(setor);
        }
        printDivisor();
        return setores;
    }

    public void criarSetor() {
        userInputProvider.setUserOutput("Nome do setor:");
        String nome = userInputProvider.getUserInput();

        Setor novoSetor = new Setor();
        novoSetor.setNome(nome);

        setorService.criarSetor(novoSetor);
        userInputProvider.setUserOutput("Setor criado com sucesso!");
    }

    public void editarSetor() {
        userInputProvider.setUserOutput("ID do setor a ser editado:");
        Long id = Long.parseLong(userInputProvider.getUserInput());

        Setor setorAnterior = setorService.buscarSetorPorId(id);
        if (setorAnterior != null) {
            userInputProvider.setUserOutput("Novo nome do setor:");
            String nome = userInputProvider.getUserInput();

            Setor setorAtualizado = new Setor();
            setorAtualizado.setNome(nome);
            printDivisor();
            userInputProvider.setUserOutput("Você tem certeza que quer alterar o Setor de:");
            printSetor(setorAnterior);
            userInputProvider.setUserOutput("Para:");
            printSetor(setorAtualizado);
            printDivisor();
            userInputProvider.setUserOutput("S/N?");
            String resposta = userInputProvider.getUserInput().trim().toUpperCase();
            if (resposta.equals("N")) {
                userInputProvider.setUserOutput("Atualização do setor foi cancelada.");
                return;
            }
            setorService.atualizarSetor(id, setorAtualizado);
            userInputProvider.setUserOutput("Setor atualizado com sucesso!");
        } else {
            userInputProvider.setUserOutput("Setor não encontrado.");
        }
    }

    public void excluirSetor() {
        userInputProvider.setUserOutput("ID do setor a ser excluído:");
        Long id = Long.parseLong(userInputProvider.getUserInput());
        Setor setorAnterior = setorService.buscarSetorPorId(id);
        if (setorAnterior != null) {
            userInputProvider.setUserOutput("Você tem certeza que quer excluir o Setor:");
            printSetor(setorAnterior);
            userInputProvider.setUserOutput("S/N?");
            String resposta = userInputProvider.getUserInput().trim().toUpperCase();
            if (resposta.equals("N")) {
                userInputProvider.setUserOutput("Exclusão do setor foi cancelada.");
                return;
            }
            setorService.excluirSetor(id);
            userInputProvider.setUserOutput("Setor excluído com sucesso!");
        } else {
            userInputProvider.setUserOutput("Setor não encontrado.");
        }

    }
}
