package book.bookwithgraphql;

import book.bookwithgraphql.domain.Book;
import book.bookwithgraphql.service.BookService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookMutation implements GraphQLMutationResolver {
    @Autowired
    private BookService bookService;

    public Book createBook(String isbnm, String author, String title, double price) {
        return bookService.createBook(isbnm, author, title, price);
    }

    public Book updateBook(String isbnm, String author, String title, double price) {
        return bookService.createBook(isbnm, author, title, price);
    }

    public Optional<Book> deleteBook(String isbn) {
        Optional<Book> book = bookService.getBook(isbn);
        bookService.delete(isbn);
        return book;
    }
}
