package bl.entities;

public class BLStudentsResults {

    private int id;
    private BLDatedLesson datedLesson;
    private BLUser student;
    private Result result;

    public BLStudentsResults(int id, BLDatedLesson datedLesson, BLUser student, Result result) {
        this.id = id;
        this.datedLesson = datedLesson;
        this.student = student;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public BLDatedLesson getDatedLesson() {
        return datedLesson;
    }

    public BLUser getStudent() {
        return student;
    }

    public Result getResult() {
        return result;
    }
}
