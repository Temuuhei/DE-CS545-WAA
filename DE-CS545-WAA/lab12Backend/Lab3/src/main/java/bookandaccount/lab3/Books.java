package bookandaccount.lab3;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class Books {
    private Collection<Book> books;

    public Books(Collection<Book> books) {this.books = books; }
    public Collection<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}
