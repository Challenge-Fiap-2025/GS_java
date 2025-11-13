package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Login;
import org.acme.service.LoginService;

import java.sql.SQLException;

@Path("/main")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    LoginService loginService;

    @POST
    @Path("/login")
    public Response cadastrarLogin(Login login) {
        try {
            boolean criado = loginService.cadastrarLogin(login);
            if(criado){
                return Response.status(Response.Status.CREATED)
                        .entity("Login cadastrado com sucesso").build();
            }
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro: Dados inválidos ao cadastrar usuário.").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao cadastrar login.").build();
        }
    }

    @DELETE
    @Path("/login/{id}")
    public Response deletarLogin(@PathParam("id") int id) {
        try {
            boolean deletado = loginService.deletarLogin(id);
            if (deletado) {
                return Response.ok("Usuário deletado com sucesso!").build();
            }
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Erro: Usuário não encontrado para o ID " + id + ".").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao deletar login.").build();
        }
    }
}