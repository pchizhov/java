package bl.entities;

public class StudentsResults {

    private int id;
    private DatedLesson datedLesson;
    private User student;
    private Result result;

    public StudentsResults(int id, DatedLesson datedLesson, User student, Result result) {
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

    public Result getResult() {
        return result;
    }
}
