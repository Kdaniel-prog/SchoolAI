package kiszel.daniel.temalabor.DTO;

import kiszel.daniel.temalabor.models.*;
import kiszel.daniel.temalabor.repository.GradesRepository;
import kiszel.daniel.temalabor.repository.SubjectsRepository;
import kiszel.daniel.temalabor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradesDTO {
    @Autowired
    private GradesRepository gradesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    public List<GradeDTO> getAllGradeDTO(){
        List<Grades> gradesList= gradesRepository.findAll();
        ArrayList<GradeDTO> gradeDTOList = new ArrayList<>();
        for (Grades grade: gradesList) {
            User user = userRepository.findByUser(grade.getUser());
            String user_name = user.getName();

            Long id = grade.getId();

            Subjects subjects = subjectsRepository.findBySubject(grade.getSubjects());
            String subject_name = subjects.getSubject();

            GradeDTO gradeDto = new GradeDTO(id, user_name, subject_name, grade.getGrade());
            gradeDTOList.add(gradeDto);
        }

        return gradeDTOList;
    }
}
