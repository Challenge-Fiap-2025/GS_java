package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.SessaoEmocional;
import org.acme.repository.SessaoEmocionalRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class SessaoEmocionalService {

    @Inject
    SessaoEmocionalRepository sessaoEmocionalRepository;

    public boolean cadastrarSessao(SessaoEmocional sessao) throws SQLException {
        if (sessao.getDataSessao() == null || sessao.getDataSessao().isEmpty()) return false;
        if (sessao.getHumor() == null || sessao.getHumor().isEmpty()) return false;
        if (sessao.getObservacoes() == null || sessao.getObservacoes().isEmpty()) return false;

        sessaoEmocionalRepository.inserirSE(sessao);
        return true;
    }

    public List<SessaoEmocional> listarSessoes() throws SQLException {
        return sessaoEmocionalRepository.listarSE();
    }

    public boolean atualizarSessao(int id, SessaoEmocional sessao) throws SQLException {
        if (id <= 0) return false;
        return sessaoEmocionalRepository.atualizarSE(id, sessao);
    }

    public boolean deletarSessao(int id) throws SQLException {
        if (id <= 0) return false;
        return sessaoEmocionalRepository.deletarSE(id);
    }



}
