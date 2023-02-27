package uz.pdp.school.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableDto {

    private int groupId;
    private int subjectId;
    private int teacherId;
    private int dayTimeId;
}
