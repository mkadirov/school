package uz.pdp.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.school.entity.Subject;
import uz.pdp.school.entity.Teacher;
import uz.pdp.school.payload.TeacherDto;
import uz.pdp.school.repository.SubjectRepository;
import uz.pdp.school.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }

    @PostMapping
    public String addTeacher(@RequestBody TeacherDto teacherDto){
        List<Subject> subjectList = new ArrayList<>();
        for (Integer subId : teacherDto.getSubjectIdList()) {
            Optional<Subject> optionalSubject = subjectRepository.findById(subId);
            if (optionalSubject.isPresent()){
                subjectList.add(optionalSubject.get());
            }else {
                return "There isn't Subject with this id";
            }
        }
        Teacher teacher = new Teacher(null, teacherDto.getFirstName(),
                teacherDto.getLastName(), subjectList);
        teacherRepository.save(teacher);
        return  "Successfully added";
    }

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable Integer id){
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        return optionalTeacher.orElseGet(Teacher::new);
    }

    @PutMapping("/{id}")
    public String editTeacher(@PathVariable Integer id, @RequestBody TeacherDto teacherDto){
        boolean isEdited = false;
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()){
            List<Subject> subjectList = new ArrayList<>();
            for (Integer subId : teacherDto.getSubjectIdList()) {
                Optional<Subject> optionalSubject = subjectRepository.findById(subId);
                if (optionalSubject.isPresent()){
                    subjectList.add(optionalSubject.get());
                }else {
                    return "There isn't Subject with this id";
                }
            }
            Teacher teacher = optionalTeacher.get();
            teacher.setFirstName(teacherDto.getFirstName());
            teacher.setLastName(teacherDto.getLastName());
            teacher.setSubjects(subjectList);
            teacherRepository.save(teacher);
            isEdited = true;
        }
        return isEdited? "Successfully edited":"There isn't Teacher with this id";
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Integer id){
        boolean isDeleted = false;
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()){
            teacherRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted? "Successfully deleted":"There isn't Teacher with this id";
    }
}
