package com.example.aulateste.setor.repository;

import com.example.aulateste.setor.entity.Setor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetorRepository {
    private final List<Setor> setores = new ArrayList<>();
    private Long nextId = 1L;

    public List<Setor> listarSetores() {
        return setores;
    }

    public Setor buscarSetorPorId(Long id) {
        for (Setor setor : setores) {
            if (setor.getId().equals(id)) {
                return setor;
            }
        }
        return null;
    }

    public Setor criarSetor(Setor setor) {
        setor.setId(nextId);
        nextId++;
        setores.add(setor);
        return setor;
    }

    public Setor atualizarSetor(Long id, Setor setor) {
        for (int i = 0; i < setores.size(); i++) {
            Setor existingSetor = setores.get(i);
            if (existingSetor.getId().equals(id)) {
                setor.setId(id);
                setores.set(i, setor);
                return setor;
            }
        }
        return null;
    }

    public boolean excluirSetor(Long id) {
        return setores.removeIf(setor -> setor.getId().equals(id));
    }
}
