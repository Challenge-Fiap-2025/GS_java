package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.AtividadeBemEstar;
import org.acme.service.AtividadeBemEstarService;

import java.sql.SQLException;
import java.util.List;

@Path("/main")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AtividadeBemEstarResource {

    @Inject
    AtividadeBemEstarService atividadeBemEstarService;

    @GET
    @Path("/atividades")
    public Response listarAtividades() {
        try {
            List<AtividadeBemEstar> lista = atividadeBemEstarService.listarAtividades();
            return Response.ok(lista).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao buscar atividades.").build();
        }
    }

    @POST
    @Path("/atividade")
    public Response cadastrarAtividade(AtividadeBemEstar atividade) {
        try {
            boolean criado = atividadeBemEstarService.cadastrarAtividade(atividade);
            if (criado) {
                return Response.status(Response.Status.CREATED)
                        .entity("Atividade de bem-estar cadastrada com sucesso!").build();
            }
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro: Dados inválidos ao cadastrar atividade.").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao cadastrar atividade.").build();
        }
    }

    @PUT
    @Path("/atividades/{id}")
    public Response atualizarAtividade(@PathParam("id") int id, AtividadeBemEstar atividade) {
        try {
            atividade.setId(id);
            boolean atualizado = atividadeBemEstarService.atualizarAtividade(id, atividade);
            if (atualizado) {
                return Response.ok("Atividade atualizada com sucesso!").build();
            }
            // Se o ID não for encontrado para atualização, retorna 404 NOT_FOUND
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Erro: Atividade não encontrada para o ID " + id + ".").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao atualizar atividade.").build();
        }
    }

    @DELETE
    @Path("/atividade/{id}")
    public Response deletarAtividade(@PathParam("id") int id) {
        try {
            boolean deletado = atividadeBemEstarService.deletarAtividade(id);
            if (deletado) {
                return Response.ok("Atividade deletada com sucesso!").build();
            }
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Erro: Atividade não encontrada para o ID " + id + ".").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno do servidor ao deletar atividade.").build();
        }
    }
}