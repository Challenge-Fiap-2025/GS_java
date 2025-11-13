package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Login;
import org.acme.repository.LoginRepository;

import java.sql.SQLException;

@ApplicationScoped
public class LoginService {

    @Inject
    LoginRepository loginRepository;

    public boolean cadastrarLogin(Login login) throws SQLException {
        if (login.getUsername() == null || login.getUsername().isEmpty()) return false;
        if (login.getPassword() == null || login.getPassword().isEmpty()) return false;

        loginRepository.inserirlogin(login);
        return true;
    }


    public boolean deletarLogin(int id) throws SQLException {
        if (id <= 0) return false;
        return loginRepository.deletarlogin(id);
    }
}
