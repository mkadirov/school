package uz.pdp.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.school.entity.DayTime;

public interface DayTimeRepository extends JpaRepository<DayTime, Integer> {
}
