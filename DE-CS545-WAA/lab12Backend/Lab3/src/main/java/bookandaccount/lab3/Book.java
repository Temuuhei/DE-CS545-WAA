package bookandaccount.lab3;

import lombok.*;

@AllArgsConstructor
@Getter @Setter
@Data
@NoArgsConstructor
public class Book {
    private String isbn;
    private String author;
    private String title;
    private double price;
}
