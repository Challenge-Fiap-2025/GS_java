package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.SessaoEmocional;
import org.acme.service.SessaoEmocionalService;

import java.sql.SQLException;
import java.util.List;

@Path("/main")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessaoEmocionalResource {

    @Inject
    SessaoEmocionalService sessaoEmocionalService;

    @GET
    @Path("/sessoes")
    public Response listarSessoes() {
        try {
            List<SessaoEmocional> lista = sessaoEmocionalService.listarSessoes();
            return Response.ok(lista).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao buscar sessões.").build();
        }
    }

    @POST
    @Path("/sessao")
    public Response cadastrarSessao(SessaoEmocional sessao) {
        try {
            boolean criado = sessaoEmocionalService.cadastrarSessao(sessao);
            if (criado) {
                return Response.status(Response.Status.CREATED)
                        .entity("Sessão emocional cadastrada com sucesso!").build();
            }
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro: Dados inválidos ou incompletos ao cadastrar sessão.").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao cadastrar sessão.").build();
        }
    }

    @PUT
    @Path("/sessoes/{id}")
    public Response atualizarSessao(@PathParam("id") int id, SessaoEmocional sessao) {
        try {
            sessao.setId(id);
            boolean atualizado = sessaoEmocionalService.atualizarSessao(id, sessao);

            if (atualizado) {
                return Response.ok("Sessão emocional atualizada com sucesso!").build();
            }

            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Erro: Sessão não encontrada para o ID " + id + ".").build();

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao atualizar sessão.").build();
        }
    }

    @DELETE
    @Path("/sessao/{id}")
    public Response deletarSessao(@PathParam("id") int id) {
        try {
            boolean deletado = sessaoEmocionalService.deletarSessao(id);

            if (deletado) {
                return Response.ok("Sessão emocional deletada com sucesso!").build();
            }

            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Erro: Sessão não encontrada para o ID " + id + ".").build();

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao deletar sessão.").build();
        }
    }
}