package com.yudi.microprofile.providers;

import com.yudi.microprofile.model.Book;
import com.yudi.microprofile.util.BookMapper;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonWriter;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

public class BookListMessageBodyWriter implements MessageBodyWriter<List<Book>> {
    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(List<Book> books, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return 0;
    }

    @Override
    public void writeTo(List<Book> books, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws WebApplicationException {
        JsonWriter jsonWriter = Json.createWriter(outputStream);
        JsonArray jsonArray = BookMapper.map(books);
        jsonWriter.writeArray(jsonArray);
        jsonWriter.close();
    }
}
