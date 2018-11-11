package db.entities;

import java.util.Date;

public class DatedLesson {

    private int id;
    private Group group;
    private User teacher;
    private String room;
    private Lesson lesson;
    private String date;
    private int number;

    public DatedLesson(int id, Group group, User teacher, String room,
                       Lesson lesson, String date, int number) {
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

    public Group getGroup() {
        return group;
    }

    public User getTeacher() {
        return teacher;
    }

    public String getRoom() {
        return room;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public String getDate() {
        return date;
    }

    public int getNumber() {
        return number;
    }
}