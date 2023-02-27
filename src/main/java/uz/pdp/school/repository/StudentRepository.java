package uz.pdp.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.school.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
