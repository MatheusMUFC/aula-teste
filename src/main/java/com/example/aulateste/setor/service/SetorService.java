package com.example.aulateste.setor.service;

import com.example.aulateste.setor.entity.Setor;
import com.example.aulateste.setor.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorService {
    private final SetorRepository setorRepository;

    @Autowired
    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public List<Setor> listarSetores() {
        return setorRepository.listarSetores();
    }

    public Setor buscarSetorPorId(Long id) {
        return setorRepository.buscarSetorPorId(id);
    }

    public Setor criarSetor(Setor setor) {
        return setorRepository.criarSetor(setor);
    }

    public Setor atualizarSetor(Long id, Setor setor) {
        return setorRepository.atualizarSetor(id, setor);
    }

    public boolean excluirSetor(Long id) {
        return setorRepository.excluirSetor(id);
    }
}
