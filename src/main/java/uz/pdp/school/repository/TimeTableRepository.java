package uz.pdp.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.school.entity.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
}
