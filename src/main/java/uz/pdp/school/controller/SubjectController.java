package uz.pdp.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.school.entity.Subject;
import uz.pdp.school.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    @PostMapping
    public String addSubject(@RequestBody Subject subject){
        subjectRepository.save(subject);
        return "Successfully added";
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable Integer id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        return optionalSubject.orElseGet(Subject::new);
    }

    @PutMapping("/{id}")
    public String editSubject(@PathVariable Integer id, @RequestBody Subject subject){
        boolean isEdited= false;
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            Subject subject1 = optionalSubject.get();
            subject1.setName(subject.getName());
            isEdited = true;
        }
        return isEdited? "Successfully edited":"There isn't Subject with this id";
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id){
        boolean isDeleted = false;
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            subjectRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted? "Successfully deleted":"There isn't Subject with this id";
    }
}
