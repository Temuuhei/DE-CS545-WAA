package bookandaccount.lab3;
//    Write BookController using REST with the following functionality:
//    addBook(Book book);
//    updateBook(Book book);
//    deleteBook(String isbn);
//    getBook(String isbn);
//    getAllBooks();
//    searchBooks(String author);
//    The Book class has the following properties: isbn, author, title, price


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private Map<String, Book> books = new HashMap<>();

    public BookController() {
        books.put("9781736049112", new Book("9781736049112",
                "Alex Xu & Sahn Lam", "System Design Interview", 90.0));
        books.put("123", new Book("123", "Temuujin", "Happy Undraa", 9999));
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        books.put(book.getIsbn(), book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        books.put(isbn, book);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return new ResponseEntity<BookErrorType>(new BookErrorType("Book with isbn = " + isbn + " is not found"), HttpStatus.NOT_FOUND);
        }
        books.remove(isbn);
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return new ResponseEntity<BookErrorType>( new BookErrorType("Book with isbn = " + isbn + " not founded"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        Books allbooks = new Books(books.values());
        System.out.println("all books " + allbooks.toString());
        return new ResponseEntity<Books>(allbooks, HttpStatus.OK);
    }

    @GetMapping("books/author/{author}")
    public ResponseEntity<?> searchBooks(@PathVariable String author) {
        Books allbooks = new Books(books.values());
        List<Book> booksByAuthor = allbooks.getBooks().stream()
                .filter(b -> b.getAuthor().equals(author))
                .collect(Collectors.toList());
        return new ResponseEntity<List<Book>>(booksByAuthor, HttpStatus.OK);
    }


}
