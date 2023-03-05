package backend_design.lab4.book.web;


import backend_design.lab4.book.domain.Book;
import backend_design.lab4.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        if (book == null) {
            return new ResponseEntity<BookErrorType>( new BookErrorType("Book with isbn = " + isbn + " is not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        bookService.add(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook( @PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        if ( book == null) {
            return new ResponseEntity<BookErrorType>(new BookErrorType("Book with isbn = " + isbn + " is not found"), HttpStatus.NOT_FOUND);
        }
        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<?> updateBook( @PathVariable String isbn, @RequestBody Book book) {
        bookService.update(book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        Books allBooks = new Books(bookService.findAll());
        return new ResponseEntity<Books>(allBooks, HttpStatus.OK);
    }

    @GetMapping("books/author/{author}")
    public ResponseEntity<?> searchBooks(@PathVariable String author) {
        Books allbooks = new Books(bookService.findAll());
        List<Book> booksByAuthor = allbooks.getBooks().stream()
                .filter(b -> b.getAuthor().equals(author))
                .collect(Collectors.toList());
        return new ResponseEntity<List<Book>>(booksByAuthor, HttpStatus.OK);
    }

}
