package uz.pdp.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.school.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
