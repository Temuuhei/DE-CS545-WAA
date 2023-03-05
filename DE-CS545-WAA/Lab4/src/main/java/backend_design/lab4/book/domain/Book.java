package backend_design.lab4.book.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class Book {
    private String isbn;
    private String author;
    private String title;
    private double price;
}
