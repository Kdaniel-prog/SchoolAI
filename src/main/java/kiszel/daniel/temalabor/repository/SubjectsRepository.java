package kiszel.daniel.temalabor.repository;

import kiszel.daniel.temalabor.models.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectsRepository extends JpaRepository<Subjects, Long> {

    List<Subjects> findAllByOrderBySubjectDesc();

    @Query("SELECT a FROM Subjects a WHERE a = :subject")
    Subjects findBySubject(@Param("subject") Subjects subject);

    @Query("SELECT a FROM Subjects a WHERE lower(a.subject) = lower(:subject_name)")
    Subjects findBySubjectName(@Param("subject_name") String subject_name);


}
