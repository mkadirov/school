package uz.pdp.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.school.entity.Mark;
import uz.pdp.school.entity.Student;
import uz.pdp.school.entity.Subject;
import uz.pdp.school.payload.MarkDto;
import uz.pdp.school.repository.MarkRepository;
import uz.pdp.school.repository.StudentRepository;
import uz.pdp.school.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mark")
public class MarkController {

    @Autowired
    MarkRepository markRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public List<Mark> getMarks(){
        return markRepository.findAll();
    }

    @PostMapping
    public String addMark(@RequestBody MarkDto markDto){
        boolean isAdded = false;
        Optional<Student> optionalStudent = studentRepository.findById(markDto.getStudentId());
        Optional<Subject> optionalSubject = subjectRepository.findById(markDto.getSubjectId());
        if(optionalStudent.isPresent() && optionalSubject.isPresent()){
            Student student = optionalStudent.get();
            Subject subject = optionalSubject.get();
            Mark mark = new Mark(null, markDto.getMark(), student, subject);
            markRepository.save(mark);
            isAdded= true;
        }
        return isAdded? "Successfully added":"There isn't subject or student with this id";
    }

    @GetMapping("/{id}")
    public Mark getMarkById(@PathVariable Integer id){
        Optional<Mark> optionalMark = markRepository.findById(id);
        return optionalMark.orElseGet(Mark::new);
    }

    @PutMapping("/{id}")
    public String editMark(@PathVariable Integer id, @RequestBody MarkDto markDto){
        boolean isEdited = false;
        Optional<Mark> optionalMark = markRepository.findById(id);
        Optional<Student> optionalStudent = studentRepository.findById(markDto.getStudentId());
        Optional<Subject> optionalSubject = subjectRepository.findById(markDto.getSubjectId());
        if(optionalMark.isPresent() && optionalSubject.isPresent() && optionalStudent.isPresent()){
            Mark mark = optionalMark.get();
            mark.setMark(markDto.getMark());
            mark.setStudent(optionalStudent.get());
            mark.setSubject(optionalSubject.get());
            markRepository.save(mark);
            isEdited = true;
        }
        return  isEdited? "Successfully edited":"There is not Mark,Student orSubject with this id";
    }

    @DeleteMapping("/{id}")
    public String deleteMark(@PathVariable Integer id){
        boolean isDeleted = false;
        Optional<Mark> optionalMark = markRepository.findById(id);
        if (optionalMark.isPresent()){
            markRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted? "Successfully deleted":"There is not Mark with this id";
    }
}
