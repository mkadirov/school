package uz.pdp.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.school.entity.Group;
import uz.pdp.school.entity.Student;
import uz.pdp.school.entity.Subject;
import uz.pdp.school.payload.StudentDto;
import uz.pdp.school.repository.GroupRepository;
import uz.pdp.school.repository.StudentRepository;
import uz.pdp.school.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    GroupRepository groupRepository;
    @GetMapping
    public List<Student> getStudentsLst(){
        return studentRepository.findAll();
    }

    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto){

        List<Subject> subjectList = new ArrayList<>();
        for (Integer subId : studentDto.getSubjectIdList()) {
            Optional<Subject> optionalSubject = subjectRepository.findById(subId);
            if (optionalSubject.isPresent()){
                subjectList.add(optionalSubject.get());
            }else {
                return "There isn't Subject with this id";
            }
        }
        Group group;
        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (optionalGroup.isPresent()){
            group= optionalGroup.get();
        }else {
            return "There isn't Group with this id";
        }
        Student student = new Student(null, studentDto.getFirstName(),
                studentDto.getLastName(), group, subjectList);
        studentRepository.save(student);
        return "Successfully added";
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElseGet(Student::new);
    }

    @PutMapping("/{id}")
    public String editStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto){
        boolean isEdited = false;
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            List<Subject> subjectList = new ArrayList<>();
            for (Integer subId : studentDto.getSubjectIdList()) {
                Optional<Subject> optionalSubject = subjectRepository.findById(subId);
                if (optionalSubject.isPresent()) {
                    subjectList.add(optionalSubject.get());
                } else {
                    return "There isn't Subject with this id";
                }
            }
            Group group;
            Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
            if (optionalGroup.isPresent()) {
                group = optionalGroup.get();
            } else {
                return "There isn't Group with this id";
            }

            student.setFistName(studentDto.getFirstName());
            student.setLastName(studentDto.getLastName());
            student.setGroup(group);
            student.setSubjects(subjectList);
            studentRepository.save(student);
            isEdited = true;
        }
        return isEdited? "Successfully edited":"There isn't Student with this id";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id){
        boolean isDeleted = false;
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            studentRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted? "Successfully deleted":"There isn't Student with this id";
    }
}
