package kiszel.daniel.temalabor.DTO;

public class GradeDTO {
    private Long id;
    private String user_name;
    private String subject_name;
    private int grade;

    public GradeDTO(Long id, String user_name, String subject_name, int grade){
        this.id = id;
        this.user_name = user_name;
        this.subject_name = subject_name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "GradeDTO{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", subject_name='" + subject_name + '\'' +
                ", grade=" + grade +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
