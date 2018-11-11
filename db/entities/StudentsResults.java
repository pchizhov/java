package db.entities;

public class StudentsResults {

    private int id;
    private DatedLesson datedLesson;
    private User student;
    private String result;

    public StudentsResults(int id, DatedLesson datedLesson, User student,
                           String result) {
        this.id = id;
        this.datedLesson = datedLesson;
        this.student = student;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public DatedLesson getDatedLesson() {
        return datedLesson;
    }

    public User getStudent() {
        return student;
    }

    public String getResult() {
        return result;
    }

}