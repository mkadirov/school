package uz.pdp.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.school.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
