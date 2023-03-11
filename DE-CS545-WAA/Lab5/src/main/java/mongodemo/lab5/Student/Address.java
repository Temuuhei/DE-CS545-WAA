package mongodemo.lab5.Student;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Address {
    private String street;
    private String city;
    private String zip;
}
