package com.yudi.microprofile.web;

import com.yudi.microprofile.model.Book;
import com.yudi.microprofile.repo.BookManager;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/books")
@RequestScoped
public class BookEndpoint {

    @Inject
    private BookManager bookManager;

    @Inject
    @ConfigProperty(name = "echo.property", defaultValue = "Provided if EchoConfigSource disabled")
    private String appProp;

    @Inject
    @ConfigProperty(name = "io_openliberty_guides_inventory_inMaintenance")
    private Provider<Boolean> inMaintenance;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") String id) {
        Book book = bookManager.get(id);
        return Response.ok(book).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        System.out.println("appProp : " + appProp);
        System.out.println("inMaintenance : " + inMaintenance.get());
        return Response.ok(bookManager.getAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        String bookId = bookManager.add(book);
        return Response.created(UriBuilder.fromResource(this.getClass()).path(bookId).build()).build();
    }
}
