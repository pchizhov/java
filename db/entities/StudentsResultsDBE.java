package db.entities;

public class StudentsResultsDBE {

    private int id;
    private DatedLessonDBE datedLesson;
    private UserDBE student;
    private String result;

    public StudentsResultsDBE(int id, DatedLessonDBE datedLesson, UserDBE student,
                           String result) {
        this.id = id;
        this.datedLesson = datedLesson;
        this.student = student;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public DatedLessonDBE getDatedLesson() {
        return datedLesson;
    }

    public UserDBE getStudent() {
        return student;
    }

    public String getResult() {
        return result;
    }

}