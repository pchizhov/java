package bl.entities;

public class BLDatedLesson {

    private int id;
    private BLGroup group;
    private BLUser teacher;
    private String room;
    private BLLesson lesson;
    private String date;
    private int number;

    public BLDatedLesson(int id, BLGroup group, BLUser teacher,
                         String room, BLLesson lesson, String date, int number) {
        this.id = id;
        this.group = group;
        this.teacher = teacher;
        this.room = room;
        this.lesson = lesson;
        this.date = date;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public BLGroup getGroup() {
        return group;
    }

    public BLUser getTeacher() {
        return teacher;
    }

    public String getRoom() {
        return room;
    }

    public BLLesson getLesson() {
        return lesson;
    }

    public String getDate() {
        return date;
    }

    public int getNumber() {
        return number;
    }
}
