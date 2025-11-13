package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.AtividadeBemEstar;
import org.acme.repository.AtividadeBemEstarRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class AtividadeBemEstarService {

    @Inject
    AtividadeBemEstarRepository atividadeBemEstarRepository;

    public boolean cadastrarAtividade(AtividadeBemEstar atividade) throws SQLException {
        if (atividade.getNome() == null || atividade.getNome().isEmpty()) return false;
        if (atividade.getCategoria() == null || atividade.getCategoria().isEmpty()) return false;
        if (atividade.getDescricao() == null || atividade.getDescricao().isEmpty()) return false;
        if (atividade.getDuracao() <= 0 ) return false;
        atividadeBemEstarRepository.inserirA(atividade);
        return true;
    }

    public List<AtividadeBemEstar> listarAtividades() throws SQLException {
        return atividadeBemEstarRepository.listarA();
    }

    public boolean atualizarAtividade(int id, AtividadeBemEstar atividade) throws SQLException {
        if (id <= 0) return false;
        return atividadeBemEstarRepository.atualizarA(id, atividade);
    }

    public boolean deletarAtividade(int id) throws SQLException {
        if (id <= 0) return false;
        return atividadeBemEstarRepository.deletarA(id);
    }
}
