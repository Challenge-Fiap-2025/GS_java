package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Usuario;
import org.acme.service.UsuarioService;

import java.sql.SQLException;
import java.util.List;

@Path("/main")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @GET
    @Path("/usuarios")
    public Response listarUsuarios() {
        try {
            List<Usuario> lista = usuarioService.listarUsuarios();
            return Response.ok(lista).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar usuários: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/usuario")
    public Response cadastrarUsuario(Usuario usuario) {
        try {
            if (usuario == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Erro: corpo da requisição está vazio.").build();
            }

            boolean criado = usuarioService.cadastrarUsuario(usuario);
            if (criado) {
                return Response.status(Response.Status.CREATED)
                        .entity("Usuário cadastrado com sucesso!").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Erro: Dados inválidos ao cadastrar usuário.").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao cadastrar usuário: " + e.getMessage()).build();
        }
    }


    @PUT
    @Path("/usuarios/{id}")
    public Response atualizarUsuario(@PathParam("id") int id, Usuario usuario) {
        try {
            if (usuario == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Erro: corpo da requisição está vazio.").build();
            }

            boolean atualizado = usuarioService.atualizarUsuario(id, usuario);
            if (atualizado) {
                return Response.ok("Usuário atualizado com sucesso!").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Erro: Dados inválidos ou usuário não encontrado.").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao atualizar usuário: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/usuario/{id}")
    public Response deletarUsuario(@PathParam("id") int id) {
        try {
            boolean deletado = usuarioService.deletarUsuario(id);
            if (deletado) {
                return Response.ok("Usuário deletado com sucesso!").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Erro: Usuário não encontrado.").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao deletar usuário: " + e.getMessage()).build();
        }
    }
}
