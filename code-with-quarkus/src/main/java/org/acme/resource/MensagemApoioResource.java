package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.MensagemApoio;
import org.acme.service.MensagemApoioService;

import java.sql.SQLException;
import java.util.List;

@Path("/main")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MensagemApoioResource {

    @Inject
    MensagemApoioService mensagemApoioService;

    @GET
    @Path("/mensagensapoiar")
    public Response listarMensagensApoio() {
        try {
            List<MensagemApoio> lista = mensagemApoioService.listarMensagens();
            return Response.ok(lista).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao buscar mensagens de apoio.").build();
        }
    }

    @POST
    @Path("/mensagensapoio")
    public Response cadastrarMensagemApoio(MensagemApoio mensagem) {
        try {
            boolean criado = mensagemApoioService.cadastrarMensagem(mensagem);
            if (criado) {
                return Response.status(Response.Status.CREATED)
                        .entity("Mensagem de apoio cadastrada com sucesso!").build();
            }
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro: Dados inválidos ao cadastrar mensagem de apoio.").build();

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao cadastrar mensagem de apoio.").build();
        }
    }

    @PUT
    @Path("/mensagensapoiar/{id}")
    public Response atualizarMensagemApoio(@PathParam("id") int id, MensagemApoio mensagem) {
        try {
            mensagem.setId(id);
            boolean atualizado = mensagemApoioService.atualizarMensagem(id, mensagem);

            if (atualizado) {
                return Response.ok("Mensagem de apoio atualizada com sucesso!").build();
            }

            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Erro: Mensagem não encontrada para o ID " + id + ".").build();

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao atualizar mensagem de apoio.").build();
        }
    }

    @DELETE
    @Path("/mensagensapoio/{id}")
    public Response deletarMensagemApoio(@PathParam("id") int id) {
        try {
            boolean deletado = mensagemApoioService.deletarMensagem(id);

            if (deletado) {
                return Response.ok("Mensagem de apoio deletada com sucesso!").build();
            }

            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Erro: Mensagem não encontrada para o ID " + id + ".").build();

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao deletar mensagem de apoio.").build();
        }
    }
}