package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Usuario;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuarioRepository {

    @Inject
    DataSource dataSource;


    public void inserirUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO T_USUARIO (nome_usuario, email_usuario, senha_usuario, tipo_usuario, idade_usuario, genero_usuario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTipo());
            ps.setInt(5, usuario.getIdade());
            ps.setString(6, usuario.getGenero());
            ps.executeUpdate();
        }
    }

    public List<Usuario> listarUsuarios()  throws SQLException{
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_USUARIO ";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome_usuario"));
                u.setEmail(rs.getString("email_usuario"));
                u.setTipo(rs.getString("tipo_usuario"));
                u.setIdade(rs.getInt("idade_usuario"));
                u.setGenero(rs.getString("genero_usuario"));
                lista.add(u);
            }

        }

        return lista;
    }

    public boolean atualizarUsuario(int id, Usuario usuario) throws SQLException {
        String sql = "UPDATE T_USUARIO  SET nome_usuario=?, email_usuario=?, senha_usuario=?, tipo_usuario=?, idade_usuario=?, genero_usuario=? WHERE id_usuario=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getTipo());
            ps.setInt(5, usuario.getIdade());
            ps.setString(6, usuario.getGenero());
            ps.setInt(7, id);
            ps.executeUpdate();
        }
        return true;
    }

    public boolean deletarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM T_USUARIO  WHERE id_usuario=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return true;
    }
}
