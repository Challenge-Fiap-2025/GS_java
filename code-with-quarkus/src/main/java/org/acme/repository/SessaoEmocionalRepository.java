package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.SessaoEmocional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SessaoEmocionalRepository {

    @Inject
    DataSource dataSource;


    public void inserirSE(SessaoEmocional s) throws SQLException {
        String sql = "INSERT INTO T_SESSAO_EMOCIONAL (data_sessao, humor_sessao, observacoes) VALUES (?, ?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getDataSessao());
            ps.setString(2, s.getHumor());
            ps.setString(3, s.getObservacoes());
            ps.executeUpdate();
        }
    }

    public List<SessaoEmocional> listarSE() throws SQLException {
        List<SessaoEmocional> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_SESSAO_EMOCIONAL";
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                SessaoEmocional s = new SessaoEmocional();
                s.setId(rs.getInt("id_sessao"));
                s.setDataSessao(rs.getString("data_sessao"));
                s.setHumor(rs.getString("humor_sessao"));
                s.setObservacoes(rs.getString("observacoes"));
                lista.add(s);
            }
        }
        return lista;
    }

    public boolean atualizarSE(int id, SessaoEmocional s) throws SQLException {
        String sql = "UPDATE T_SESSAO_EMOCIONAL SET data_sessao=?, humor_sessao=?, observacoes=? WHERE id_sessao=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getDataSessao());
            ps.setString(2, s.getHumor());
            ps.setString(3, s.getObservacoes());
            ps.setInt(4, id);
            ps.executeUpdate();
        }
        return true;
    }

    public boolean deletarSE(int id) throws SQLException {
        String sql = "DELETE FROM  T_SESSAO_EMOCIONAL WHERE id_sessao=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return true;
    }
}
