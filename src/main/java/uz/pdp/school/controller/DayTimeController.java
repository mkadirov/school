package uz.pdp.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.school.entity.DayTime;
import uz.pdp.school.repository.DayTimeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/daytime")
public class DayTimeController {

    @Autowired
    DayTimeRepository dayTimeRepository;

    @GetMapping
    public List<DayTime> getDayTimeList(){
        return dayTimeRepository.findAll();
    }

    @PostMapping
    public String addDayTime(@RequestBody DayTime dayTime){

        dayTimeRepository.save(dayTime);
        return "Successfully added";
    }

    @GetMapping("/{id}")
    public DayTime getDayTimeById(@PathVariable Integer id){
        Optional<DayTime> optionalDayTime = dayTimeRepository.findById(id);
        return optionalDayTime.orElseGet(DayTime::new);
    }

    @PutMapping("/{id}")
    public String getDayTime(@PathVariable Integer id, @RequestBody DayTime dayTime){
        boolean isEdited = false;
        Optional<DayTime> optionalDayTime = dayTimeRepository.findById(id);
        if (optionalDayTime.isPresent()){
            DayTime tempDayTime = optionalDayTime.get();
           dayTime.setId(tempDayTime.getId());
           dayTimeRepository.save(dayTime);
           isEdited = true;
        }
        return isEdited? "Successfully edited":"There isn't DayTime with with id"+ id;
    }


    @DeleteMapping("/{id}")
    public String deleteDayTime(@PathVariable Integer id){
        boolean isDeleted = false;
        Optional<DayTime> optionalDayTime = dayTimeRepository.findById(id);
        if (optionalDayTime.isPresent()){
            dayTimeRepository.deleteById(id);
            isDeleted = true;
        }
        return isDeleted? "Successfully deleted":"There isn't DayTime with with id"+ id;
    }
}
