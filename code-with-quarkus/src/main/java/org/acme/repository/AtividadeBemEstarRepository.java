package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.AtividadeBemEstar;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AtividadeBemEstarRepository {

    @Inject
    DataSource dataSource;

    public void inserirA(AtividadeBemEstar a) throws SQLException{
        String sql = "INSERT INTO T_ATIVIDADE_BEM_ESTAR  (nome, descricao, categoria, duracao) VALUES (?, ?, ?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getNome());
            ps.setString(2, a.getDescricao());
            ps.setString(3, a.getCategoria());
            ps.setInt(4, a.getDuracao());
            ps.executeUpdate();
        }
    }

    public List<AtividadeBemEstar> listarA() throws SQLException {
        List<AtividadeBemEstar> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_ATIVIDADE_BEM_ESTAR ";
        try (Connection con = dataSource.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                AtividadeBemEstar a = new AtividadeBemEstar();
                a.setId(rs.getInt("id_atividade"));
                a.setNome(rs.getString("nome"));
                a.setDescricao(rs.getString("descricao"));
                a.setCategoria(rs.getString("categoria"));
                a.setDuracao(rs.getInt("duracao"));
                lista.add(a);
            }
        }
        return lista;
    }

    public boolean atualizarA(int id, AtividadeBemEstar a)  throws SQLException{
        String sql = "UPDATE T_ATIVIDADE_BEM_ESTAR SET nome=?, descricao=?, categoria=?, duracao=? WHERE id_atividade=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getNome());
            ps.setString(2, a.getDescricao());
            ps.setString(3, a.getCategoria());
            ps.setInt(4, a.getDuracao());
            ps.setInt(5, id);
            ps.executeUpdate();
        }
        return true;
    }

    public boolean deletarA(int id) throws SQLException{
        String sql = "DELETE FROM T_ATIVIDADE_BEM_ESTAR  WHERE id_atividade=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return true;
    }
}
