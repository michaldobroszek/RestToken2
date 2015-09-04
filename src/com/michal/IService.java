package com.michal;

import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/log")
@Produces("application/json")
public interface IService {

	@GET
	@Path("/{login}/{password}")
	public User getLogin(@PathParam("login") String login,
			@PathParam("password") String password);

	@GET
	@Path("/login/{token}")
	public Response writeSomething(@PathParam("token") UUID token);
	
	@GET
	@Path("/books/{id}/{token}")
	public Response getBooksById(@PathParam("id") int id,@PathParam("token") UUID token);
	
	
	@GET
	@Path("/books/{id}/{title}/{author}/{token}")
	public Response addBook(@PathParam("id") int id,@PathParam("title") String title,@PathParam("author") String author,@PathParam("token") UUID token);
	
	@GET
	@Path("/books/all/{token}")
	public Response getAllBook(@PathParam("token") UUID token);

}