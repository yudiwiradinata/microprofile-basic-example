package com.yudi.microprofile.providers;


import com.yudi.microprofile.model.Book;
import com.yudi.microprofile.util.BookMapper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class BookMessageBodyWriter implements MessageBodyWriter<Book> {
    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return type.equals(Book.class);
    }

    @Override
    public long getSize(Book book, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return 0;
    }

    @Override
    public void writeTo(Book book, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws WebApplicationException {
        JsonWriter jsonWriter = Json.createWriter(outputStream);
        JsonObject jsonObject = BookMapper.map(book);
        jsonWriter.writeObject(jsonObject);
        jsonWriter.close();
    }
}
