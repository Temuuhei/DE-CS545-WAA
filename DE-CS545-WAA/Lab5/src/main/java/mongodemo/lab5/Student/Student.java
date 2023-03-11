package mongodemo.lab5.Student;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Setter
@Data
@NoArgsConstructor
@Document
public class Student {
    @Id
    private int studentNumber;
    private String name;
    private String phoneNumber;
    private String email;
    private Address address;

    public Student(int studentNumber, String name, String phone, String email) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.phoneNumber = phone;
        this.email = email;
    }
}
