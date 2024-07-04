
package com.algonquin.cst8288.fwrps.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author renxihai
 */
@Path("books")
public class BookExample {
    private static final List<com.algonquin.cst8288.fwrps.model.BookExample> books = new ArrayList<>();

    static {
        books.add(new com.algonquin.cst8288.fwrps.model.BookExample(1L, "Book One", "Author One"));
        books.add(new com.algonquin.cst8288.fwrps.model.BookExample(2L, "Book Two", "Author Two"));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<com.algonquin.cst8288.fwrps.model.BookExample> getBooks() {
        return books;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") Long id) {
        Optional<com.algonquin.cst8288.fwrps.model.BookExample> book = books.stream().filter(b -> b.getId().equals(id)).findFirst();
        if (book.isPresent()) {
            return Response.ok(book.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(com.algonquin.cst8288.fwrps.model.BookExample book) {
        books.add(book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") Long id, com.algonquin.cst8288.fwrps.model.BookExample updatedBook) {
        for (com.algonquin.cst8288.fwrps.model.BookExample book : books) {
            if (book.getId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                return Response.ok(book).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("id") Long id) {
        Optional<com.algonquin.cst8288.fwrps.model.BookExample> book = books.stream().filter(b -> b.getId().equals(id)).findFirst();
        if (book.isPresent()) {
            books.remove(book.get());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
}
