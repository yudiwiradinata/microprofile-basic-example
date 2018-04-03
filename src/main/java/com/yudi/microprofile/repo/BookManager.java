package com.yudi.microprofile.repo;

import com.yudi.microprofile.model.Book;
import org.eclipse.microprofile.config.Config;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class BookManager {

    private ConcurrentMap<String, Book> inMemoryStore = new ConcurrentHashMap<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
    private AtomicInteger bookIdGenerator = new AtomicInteger(0);

    @Inject
    Config config;

    public BookManager() {
    }

    @PostConstruct
    void init() {
        Book book = new Book();
        book.setId(getNextId());
        book.setName(config.getValue("book.name", String.class));
        book.setIsbn(config.getValue("isbn.no", String.class));
        book.setAuthor(config.getValue("author.name", String.class));
        book.setPages(config.getValue("page.no", Integer.class));

        inMemoryStore.put(book.getId(), book);
    }

    private String getNextId() {
        String date = LocalDate.now().format(formatter);
        //return String.format("%04d-%s", bookIdGenerator.incrementAndGet(), date);
        return String.format(config.getValue("format.id", String.class), bookIdGenerator.incrementAndGet(), date);
    }

    public String add(Book book) {
        String id = getNextId();
        book.setId(id);
        inMemoryStore.put(id, book);
        return id;
    }

    public Book get(String id) {
        return inMemoryStore.get(id);
    }

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        books.addAll(inMemoryStore.values());
        return books;
    }

}
