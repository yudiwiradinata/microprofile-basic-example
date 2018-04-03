package com.yudi.microprofile.providers;

import com.yudi.microprofile.model.Book;
import com.yudi.microprofile.util.BookMapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class BookMessageBodyReader implements MessageBodyReader<Book> {
    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return type.equals(Book.class);
    }

    @Override
    public Book readFrom(Class<Book> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws WebApplicationException {
        return BookMapper.map(inputStream);
    }
}
