package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.MensagemApoio;
import org.acme.repository.MensagemApoioRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class MensagemApoioService {

    @Inject
    MensagemApoioRepository mensagemDeApoioRepository;

    public boolean cadastrarMensagem(MensagemApoio mensagem) throws SQLException {
        if (mensagem.getTitulo() == null || mensagem.getTitulo().isEmpty()) return false;
        if (mensagem.getConteudo() == null || mensagem.getConteudo().isEmpty()) return false;
        if (mensagem.getTipo() == null || mensagem.getTipo().isEmpty()) return false;
        if (mensagem.getDataEnvio() == null || mensagem.getDataEnvio().isEmpty()) return false;

        mensagemDeApoioRepository.inserir(mensagem);
        return true;
    }

    public List<MensagemApoio> listarMensagens() throws SQLException {
        return mensagemDeApoioRepository.listar();
    }

    public boolean atualizarMensagem(int id, MensagemApoio mensagem) throws SQLException {
        if (id <= 0) return false;
        if (mensagem.getConteudo() == null || mensagem.getConteudo().isEmpty()) return false;
        return mensagemDeApoioRepository.atualizar(id, mensagem);
    }

    public boolean deletarMensagem(int id) throws SQLException {
        if (id <= 0) return false;
        return mensagemDeApoioRepository.deletar(id);
    }
}
