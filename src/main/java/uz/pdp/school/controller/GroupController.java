package uz.pdp.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.school.entity.Group;
import uz.pdp.school.entity.School;
import uz.pdp.school.payload.GroupDto;
import uz.pdp.school.repository.GroupRepository;
import uz.pdp.school.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SchoolRepository schoolRepository;

    @GetMapping()
    public List<Group> getGroupsList(){
        return groupRepository.findAll();
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto){
        boolean isAdded = false;
        Group group = new Group();
        Optional<School> optionalSchool = schoolRepository.findById(groupDto.getSchoolId());
        if (optionalSchool.isPresent()){
            group.setName(groupDto.getName());
            group.setSchool(optionalSchool.get());
            groupRepository.save(group);
            isAdded = true;
        }
        return isAdded? "Successfully added": "There isn't school with ID:"+ groupDto.getSchoolId();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Integer id){
        Optional<Group> optionalGroup = groupRepository.findById(id);
        return optionalGroup.orElseGet(Group::new);
    }

    @PutMapping("/{id}")
    public String editGroup(@PathVariable Integer id, @RequestBody GroupDto groupDto){
        boolean isEdited = false;
        Optional<Group> optionalGroup = groupRepository.findById(id);
        Optional<School> optionalSchool = schoolRepository.findById(groupDto.getSchoolId());
        if(optionalGroup.isPresent() && optionalSchool.isPresent()){
            Group group = optionalGroup.get();
            group.setName(groupDto.getName());
            group.setSchool(optionalSchool.get());
            groupRepository.save(group);
            isEdited = true;
        }
        return isEdited? "Successfully edited":"There isn't School or Group with this id";
    }

    @DeleteMapping("/{id}")
    public String deleteGroupById(@PathVariable Integer id){
        boolean isDeleted = false;
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if(optionalGroup.isPresent()){
            groupRepository.deleteById(id);
            isDeleted= true;
        }
        return isDeleted? "Successfully deleted":"There isn't group with this id";
    }
}
