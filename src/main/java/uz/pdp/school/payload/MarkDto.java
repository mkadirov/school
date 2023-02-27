package uz.pdp.school.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkDto {

    private int mark;

    private int studentId;

    private int subjectId;
}
