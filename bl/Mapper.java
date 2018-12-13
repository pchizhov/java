package bl;

import bl.entities.*;
import db.entities.*;

public class Mapper {

    public static StudentsResults map(StudentsResultsDBE sR) {
        int id = sR.getId();
        DatedLesson datedLesson = Mapper.map(sR.getDatedLesson());
        User student = Mapper.map(sR.getStudent());
        Result result = Result.valueOf(sR.getResult());
        return new StudentsResults(id, datedLesson, student, result);
    }

    public static Group map(GroupDBE g) {
        String number = g.getNumber();
        String level = g.getLevel();
        return new Group(number, level);
    }

    public static DatedLesson map(DatedLessonDBE dL) {
        int id = dL.getId();
        Group group = Mapper.map(dL.getGroup());
        User teacher = Mapper.map(dL.getTeacher());
        String room = dL.getRoom();
        Lesson lesson = Mapper.map(dL.getLesson());
        String date = dL.getDate();
        int number = dL.getNumber();
        return new DatedLesson(id, group, teacher, room, lesson, date, number);
    }

    public static User map(UserDBE u) {
        String login = u.getLogin();
        String name = u.getName();
        Role role = Role.valueOf(u.getRole());
        Group group = null;
        if (u.getGroup() != null)
            group = Mapper.map(u.getGroup());
        return new User(login, name, role, group);
    }

    public static Lesson map(LessonDBE l){
        String name = l.getName();
        String description = l.getDescription();
        return new Lesson(name, description);
    }
}
