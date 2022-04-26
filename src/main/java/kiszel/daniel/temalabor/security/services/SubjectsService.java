package kiszel.daniel.temalabor.security.services;

import kiszel.daniel.temalabor.models.Subjects;
import kiszel.daniel.temalabor.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectsService {
    @Autowired
    private SubjectsRepository subjectsRepository;

    public List<Subjects> getSubjects(){
        return subjectsRepository.findAllByOrderBySubjectDesc();
    }
    public void editSubjects(Subjects subjects){
        subjectsRepository.save(subjects);
    }
    public void deleteSubjects(Long id){
        subjectsRepository.deleteById(id);
    }
}
