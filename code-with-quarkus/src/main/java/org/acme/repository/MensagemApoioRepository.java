package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.MensagemApoio;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MensagemApoioRepository {

    @Inject
    DataSource dataSource;

    public void inserir(MensagemApoio m) throws SQLException {
        String sql = "INSERT INTO T_MENSAGEM_APOIO (titulo, conteudo, tipo_mensagem, data_envio) VALUES (?, ?, ?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getTitulo());
            ps.setString(2, m.getConteudo());
            ps.setString(3, m.getTipo());
            ps.setString(4, m.getDataEnvio());
            ps.executeUpdate();
        }
    }

    public List<MensagemApoio> listar() throws SQLException {
        List<MensagemApoio> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_MENSAGEM_APOIO";
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                MensagemApoio m = new MensagemApoio();
                m.setId(rs.getInt("id_mensagem"));
                m.setTitulo(rs.getString("titulo"));
                m.setConteudo(rs.getString("conteudo"));
                m.setTipo(rs.getString("tipo_mensagem"));
                m.setDataEnvio(rs.getString("data_envio"));
                lista.add(m);
            }
        }
        return lista;
    }

    public boolean atualizar(int id, MensagemApoio m) throws SQLException {
        String sql = "UPDATE T_MENSAGEM_APOIO SET titulo=?, conteudo=?, tipo_mensagem=?, data_envio=? WHERE id_mensagem=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getTitulo());
            ps.setString(2, m.getConteudo());
            ps.setString(3, m.getTipo());
            ps.setString(4, m.getDataEnvio());
            ps.setInt(5, id);
            ps.executeUpdate();
        }
        return true;
    }

    public boolean deletar(int id) throws SQLException{
        String sql = "DELETE FROM T_MENSAGEM_APOIO WHERE id_mensagem=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return true;
    }

}
