package br.com.app.restful.resources;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.app.restful.dao.ClienteDAO;
import br.com.app.restful.model.Cliente;

@Path("/clientes")
public class ClienteResource {
	
	@Inject
	ClienteDAO dao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		return Response.ok(dao.listar()).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetch(@PathParam("id") Long id) {
		return Response.ok(dao.buscar(id)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Cliente cliente) {
		URI location = UriBuilder.fromResource(ClienteResource.class).path(dao.salvar(cliente).toString()).build();
		return Response.created(location).build();
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, Cliente cliente) {
		URI location = UriBuilder.fromResource(ClienteResource.class).path(cliente.getId().toString()).build();
		return Response.ok(dao.salvar(cliente)).header("Location", location).build();
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remover(@PathParam("id") Long id) {
		dao.remover(id);
		return Response.noContent().build();
	}
	
}
