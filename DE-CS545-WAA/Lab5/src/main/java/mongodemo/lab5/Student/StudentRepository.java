package mongodemo.lab5.Student;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository <Student, Integer> {
    @Query("{ name :  ?0 }")
    List<Student> findByName(String name);
    Student findByPhoneNumber(String phone);
    @Query("{ 'address.city' :  ?0}" )
    List<Student> findStudentByAddress_City(String city);

}
