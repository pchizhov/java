package db.entities;

import java.util.Date;

public class DatedLessonDBE {

    private int id;
    private GroupDBE group;
    private UserDBE teacher;
    private String room;
    private LessonDBE lesson;
    private String date;
    private int number;

    public DatedLessonDBE(int id, GroupDBE group, UserDBE teacher, String room,
                       LessonDBE lesson, String date, int number) {
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

    public GroupDBE getGroup() {
        return group;
    }

    public UserDBE getTeacher() {
        return teacher;
    }

    public String getRoom() {
        return room;
    }

    public LessonDBE getLesson() {
        return lesson;
    }

    public String getDate() {
        return date;
    }

    public int getNumber() {
        return number;
    }
}