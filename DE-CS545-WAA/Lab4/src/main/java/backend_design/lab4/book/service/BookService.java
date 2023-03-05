package backend_design.lab4.book.service;


import backend_design.lab4.book.data.BookRepository;
import backend_design.lab4.book.domain.Book;
import backend_design.lab4.book.integration.EmailSender4Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    EmailSender4Bank emailSender;

    public void add (Book book) {
        bookRepository.save(book);
        emailSender.sendEmail(book.getIsbn(), "Added");
    }

    public void update (Book book) {
        bookRepository.save(book);
    }

    public Book findByIsbn (String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public void delete (String isbn) {
        Book book = bookRepository.findByIsbn(isbn);
        emailSender.sendEmail(book.getIsbn(), "Deleted");
        bookRepository.delete(isbn);
    }

    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }
}
