package uz.pdp.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.school.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
