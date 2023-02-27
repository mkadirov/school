package uz.pdp.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.school.entity.*;
import uz.pdp.school.payload.TimeTableDto;
import uz.pdp.school.repository.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timetable")
public class TimeTableController {

    @Autowired
    TimeTableRepository timeTableRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    DayTimeRepository dayTimeRepository;

    @GetMapping
    public List<TimeTable> getTimeTableList(){
        return timeTableRepository.findAll();
    }

    @PostMapping
    public String addTimeTable(@RequestBody TimeTableDto timeTableDto){

        boolean isAdded = false;

        Optional<Group> opGroup = groupRepository.findById(timeTableDto.getGroupId());
        Optional<Subject> opSub = subjectRepository.findById(timeTableDto.getSubjectId());
        Optional<Teacher> opTeach = teacherRepository.findById(timeTableDto.getTeacherId());
        Optional<DayTime> opDT = dayTimeRepository.findById(timeTableDto.getDayTimeId());

        if(opGroup.isPresent()&& opSub.isPresent()&& opTeach.isPresent()&& opDT.isPresent()){
            TimeTable timeTable = new TimeTable(null, opGroup.get(),
                    opSub.get(), opTeach.get(), opDT.get());

            timeTableRepository.save(timeTable);
            isAdded= true;
        }
        return isAdded? "Successfully added":"There isn't Group, Subject, Teacher or DayTime with this id";
    }

    @GetMapping("/{id}")
    public TimeTable getTimeTableById(@PathVariable Integer id){
        Optional<TimeTable> timeTableOptional = timeTableRepository.findById(id);
        return timeTableOptional.orElseGet(TimeTable::new);
    }

    @PutMapping("/{id}")
    public String editTimeTable(@PathVariable Integer id, @RequestBody TimeTableDto timeTableDto){
        boolean isEdited = false;
        Optional<TimeTable> opTable = timeTableRepository.findById(id);
        Optional<Group> opGroup = groupRepository.findById(timeTableDto.getGroupId());
        Optional<Subject> opSub = subjectRepository.findById(timeTableDto.getSubjectId());
        Optional<Teacher> opTeach = teacherRepository.findById(timeTableDto.getTeacherId());
        Optional<DayTime> opDT = dayTimeRepository.findById(timeTableDto.getDayTimeId());

        if(opTable.isPresent() &&opGroup.isPresent()&& opSub.isPresent()&&
                opTeach.isPresent()&& opDT.isPresent()){

            TimeTable timeTable = opTable.get();
            timeTable.setGroup(opGroup.get());
            timeTable.setSubject(opSub.get());
            timeTable.setTeacher(opTeach.get());
            timeTable.setDayTime(opDT.get());

            timeTableRepository.save(timeTable);
            isEdited= true;

        }
        return isEdited? "Successfully edited":"There isn't Group, Subject, Teacher or DayTime with this id";
    }

    @DeleteMapping("/{id}")
    public String deleteTimeTable(@PathVariable Integer id){
        boolean isDeleted = false;
        Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(id);
        if (optionalTimeTable.isPresent()){
            timeTableRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted? "Successfully deleted":"There isn't TimeTable with this id";
    }
}
