package kiszel.daniel.temalabor.controllers;

import kiszel.daniel.temalabor.models.Subjects;
import kiszel.daniel.temalabor.payload.response.MessageResponse;
import kiszel.daniel.temalabor.repository.SubjectsRepository;
import kiszel.daniel.temalabor.security.services.SubjectsService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class SubjectsController {

    @Autowired
    private SubjectsService service;

    @Autowired
    SubjectsRepository subjectsRepository;

    @GetMapping("/subjects")
    public List<Subjects> getSubjects(){
        return service.getSubjects();
    }

    @PostMapping("/add_subjects")
    public ResponseEntity<?> addNews(@Valid @RequestBody JSONObject param){
        JSONObject params = new JSONObject(param);
        Subjects subjects = new Subjects();
        subjects.setSubject((String) params.get("subject"));
        subjectsRepository.save(subjects);
        return ResponseEntity.ok(new MessageResponse("News created successfully!"));
    }

    @PutMapping("/subjects/{id}/edit")
    public void editSubjects(@PathVariable("id") Long id, @RequestBody Subjects subjects){
        service.editSubjects(subjects);
    }
    @DeleteMapping("/subjects/{id}/delete")
    public void deleteSubjects(@PathVariable("id") Long id){
        service.deleteSubjects(id);
    }

}

