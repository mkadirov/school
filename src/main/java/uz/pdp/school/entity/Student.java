package uz.pdp.school.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String fistName;
    @Column(nullable = false)
    private String lastName;
    @ManyToOne
    private Group group;
    @ManyToMany
    private List<Subject> subjects;
}
