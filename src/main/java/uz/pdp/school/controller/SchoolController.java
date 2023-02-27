package uz.pdp.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.school.entity.School;
import uz.pdp.school.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    SchoolRepository schoolRepository;

    @GetMapping
    public List<School> getSchoolList(){
        return schoolRepository.findAll();
    }

    @PostMapping
    public String addSchool(@RequestBody School school){
        schoolRepository.save(school);
        return "Successfully added";
    }

    @GetMapping("/{id}")
    public School getSchoolById(@PathVariable Integer id){
        Optional<School> optionalSchool = schoolRepository.findById(id);
        return optionalSchool.orElseGet(School::new);
    }

    @PutMapping("/{id}")
    public String editSchool(@PathVariable Integer id, @RequestBody School school){
        boolean isEdited = false;
        Optional<School> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()){
            School editingSchool = optionalSchool.get();
            editingSchool.setName(school.getName());
            isEdited = true;
        }
        return isEdited? "Successfully edited":"There isn't School with ID:" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteSchool(@PathVariable Integer id){
        boolean isDeleted = false;
        Optional<School> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()){
            schoolRepository.deleteById(id);
            isDeleted= true;
        }
        return isDeleted? "Successfully deleted":"There isn't School with ID:" + id;
    }
}
