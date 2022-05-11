package kiszel.daniel.temalabor.security.services;

import kiszel.daniel.temalabor.DTO.GradeDTO;
import kiszel.daniel.temalabor.DTO.GradesDTO;
import kiszel.daniel.temalabor.models.*;
import kiszel.daniel.temalabor.payload.response.MessageResponse;
import kiszel.daniel.temalabor.repository.GradesRepository;
import kiszel.daniel.temalabor.repository.SubjectsRepository;
import kiszel.daniel.temalabor.repository.UserRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@Service
public class GradesService {

    @Autowired
    private GradesRepository gradesRepository;

    @Autowired
    private GradesDTO gradesDTO;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    public List<GradeDTO> getAllGrade() {
        return gradesDTO.getAllGradeDTO();
    }
    public List<GradeDTO> getCurrentStudentGrade(Long id){
        return gradesDTO.getCurrentUserGrade(id);
    }

    public List<User> getStudents(){
        List<User> users= userRepository.findAll();
        ArrayList<User> student_user = new ArrayList<>();
        for(User user: users){
            if(userRepository.findRole(user).getName().toString().equals(ERole.ROLE_STUDENT.toString())){
                student_user.add(user);
            }else{
                continue;
            }
        }
        return student_user;
    }

    public ResponseEntity<MessageResponse> addGrades(@Valid JSONObject param){
        JSONObject params = new JSONObject(param);
        Grades grades = new Grades();

        Subjects subjects = subjectsRepository.findBySubjectName((String) params.get("subject_name"));
        grades.setSubjects(subjects);

        User user = userRepository.findByName((String) params.get("user_name"));
        grades.setUser(user);

        grades.setGrade((int) params.get("grade"));

        gradesRepository.save(grades);
        return ResponseEntity.ok(new MessageResponse("News created successfully!"));
    }

    public void editGrades(JSONObject param, Long id){
        JSONObject params = new JSONObject(param);
        Grades grades = new Grades();
        grades.setId(id);
        grades.setGrade(Integer.valueOf((String) params.get("grade")));

        Subjects subjects = subjectsRepository.findBySubjectName((String) params.get("subject_name"));
        grades.setSubjects(subjects);

        User user = userRepository.findByName((String) params.get("user_name"));
        grades.setUser(user);

        gradesRepository.save(grades);
    }

    public void deleteGrades(Long id){
        gradesRepository.deleteById(id);
    }

}
