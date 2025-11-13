package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Usuario;
import org.acme.repository.UsuarioRepository;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public boolean cadastrarUsuario(Usuario usuario) throws SQLException {
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) return false;
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) return false;
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) return false;
        if (usuario.getTipo() == null || usuario.getTipo().isEmpty()) return false;
        if (usuario.getIdade() <= 0) return false;
        if (usuario.getGenero() == null || usuario.getGenero().isEmpty()) return false;

        usuarioRepository.inserirUsuario(usuario);
        return true;
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        return usuarioRepository.listarUsuarios();
    }

    public boolean atualizarUsuario(int id, Usuario usuario) throws SQLException {
        if (id <= 0) return false;
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) return false;
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) return false;
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) return false;
        if (usuario.getTipo() == null || usuario.getTipo().isEmpty()) return false;
        if (usuario.getIdade() <= 0) return false;
        if (usuario.getGenero() == null || usuario.getGenero().isEmpty()) return false;

        return usuarioRepository.atualizarUsuario(id, usuario);
    }

    public boolean deletarUsuario(int id) throws SQLException {
        if (id <= 0) return false;
        return usuarioRepository.deletarUsuario(id);
    }


}
