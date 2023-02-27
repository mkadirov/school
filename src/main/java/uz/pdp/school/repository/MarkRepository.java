package uz.pdp.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.school.entity.Mark;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
}
