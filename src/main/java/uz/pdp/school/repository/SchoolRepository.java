package uz.pdp.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.school.entity.School;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
