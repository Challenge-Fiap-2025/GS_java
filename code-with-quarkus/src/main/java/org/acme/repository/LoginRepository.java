package org.acme.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Login;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@ApplicationScoped
public class LoginRepository {

    @Inject
    DataSource dataSource;

    public void inserirlogin(Login login) throws SQLException {
        String sql = "INSERT INTO T_LOGIN (usuario,senha) VALUES (?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, login.getUsername());
            ps.setString(2, login.getPassword() );
            ps.executeUpdate();
        }
    }

    public boolean deletarlogin(int id) throws SQLException {
        String sql = "DELETE FROM T_LOGIN  WHERE id=?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        return true;
    }
}
