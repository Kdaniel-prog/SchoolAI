package kiszel.daniel.temalabor.controllers;

import kiszel.daniel.temalabor.models.GradeDTO;
import kiszel.daniel.temalabor.models.User;
import kiszel.daniel.temalabor.security.services.GradesService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class GradesController {

    @Autowired
    private GradesService service;

    @GetMapping("/student_users")
    public List<User> getUsers(){
        return service.getStudents();
    }

    @GetMapping("/grades/{id}/current_student_grade")
    public List<GradeDTO> CurrentStudentGrade(@PathVariable("id") Long id){
        return service.getCurrentStudentGrade(id);
    }

    @GetMapping("/grades")
    public List<GradeDTO> getGrades(){
        return service.getAllGrade();
    }

    @PostMapping("/grades/add_grades")
    public ResponseEntity<?> addGrade(@Valid @RequestBody JSONObject param){
        return service.addGrades(param);
    }

    @PutMapping("/grades/{id}/edit")
    public void editGrades(@PathVariable("id") Long id, @RequestBody JSONObject param){
        service.editGrades(param,id);
    }

    @DeleteMapping("/grades/{id}/delete")
    public void deleteGrades(@PathVariable("id") Long id){
        service.deleteGrades(id);
    }
}
