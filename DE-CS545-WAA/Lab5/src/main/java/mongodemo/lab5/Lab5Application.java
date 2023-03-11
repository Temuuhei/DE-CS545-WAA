package mongodemo.lab5;

import mongodemo.lab5.Student.StudentRepository;
import mongodemo.lab5.Student.Address;
import mongodemo.lab5.Student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;

@SpringBootApplication
public class Lab5Application implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(Lab5Application.class, args);
    }

    @Override
    public void run (String... args) throws Exception {
        Student student = new Student(1, "Temka", "1111111", "ttsogt@miu.edu");
        Address address = new Address("1000N 8th St", "Fairfield", "52556");
        student.setAddress(address);
        studentRepository.save(student);

        student = new Student(2,"John 2", "22222222", "john2@acme.com");
        address = new Address("Street 2", "City2", "22222");
        student.setAddress(address);
        studentRepository.save(student);

        student = new Student(3,"John 3", "33333333", "john3@acme.com");
        address = new Address("Street 3", "City1", "33333");
        student.setAddress(address);
        studentRepository.save(student);

        //get students
        System.out.println(studentRepository.findById(1).get());
        System.out.println(studentRepository.findById(2).get());
        System.out.println(studentRepository.findById(3).get());

        System.out.println("-----------All students ----------------");
        System.out.println(studentRepository.findAll());
        //update student
        student = studentRepository.findById(1).get();
        student.setEmail("john1@gmail.com");
        studentRepository.save(student);
        System.out.println("-----------find by name ----------------");
        System.out.println(studentRepository.findByName("Temka"));
        System.out.println("-----------find by phone ----------------");
        System.out.println(studentRepository.findByPhoneNumber("22222222"));
        System.out.println("-----------find students living in a certain city ----------------");
        List<Student> customers = studentRepository.findStudentByAddress_City("City1");
        for (Student cust : customers){
            System.out.println(cust);
        }
    }

}
