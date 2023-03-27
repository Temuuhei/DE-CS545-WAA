package book.bookwithgraphql.service;

import book.bookwithgraphql.data.BookRepository;
import book.bookwithgraphql.domain.Book;
import book.bookwithgraphql.integration.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    Map<String, Book> books = new HashMap<>();
//    @Autowired
//    BookRepository bookRepository;

    @Autowired
    EmailSender emailSender;

//    public void add (Book book) {
//        bookRepository.save(book);
//        emailSender.sendEmail(book.getIsbn(), "Added");
//    }
//
//    public void update (Book book) {
//        bookRepository.save(book);
//    }
//
//    public Book findByIsbn(String isbn) {
//        return bookRepository.findByIsbn(isbn);
//    }
//
//    public void delete (String isbn) {
//        Book book = bookRepository.findByIsbn(isbn);
//        emailSender.sendEmail(book.getIsbn(), "Deleted");
//        bookRepository.delete(isbn);
//    }
//
//    public Collection<Book> findAll() {
//        return bookRepository.findAll();
//    }

    public List<Book> getAllBooks(int count) {
        List<Book> bookList = books.values().stream()
                .collect(Collectors.toList());
        return bookList.subList(0, count);
    }

    public Optional<Book> getBook(String isbn) {
        return Optional.of(books.get(isbn));
    }

    public Book createBook(String isbn, String author, String title, double price) {
        Book book = new Book(isbn, author, title, price);
        books.put(isbn, book);
        return book;
    }

    public void delete(String isbn) {
        emailSender.sendEmail("Book deleted with " + isbn + " ", "deleted");
        books.remove(isbn);
    }
}
