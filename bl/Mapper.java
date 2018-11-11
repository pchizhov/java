package bl;

import bl.entities.*;
import db.entities.*;

public class Mapper {

    public static BLStudentsResults map(StudentsResults sR) {
        int id = sR.getId();
        BLDatedLesson datedLesson = Mapper.map(sR.getDatedLesson());
        BLUser student = Mapper.map(sR.getStudent());
        Result result = Result.valueOf(sR.getResult());
        return new BLStudentsResults(id, datedLesson, student, result);
    }

    public static BLGroup map(Group g) {
        String number = g.getNumber();
        String level = g.getLevel();
        return new BLGroup(number, level);
    }

    public static BLDatedLesson map(DatedLesson dL) {
        int id = dL.getId();
        BLGroup group = Mapper.map(dL.getGroup());
        BLUser teacher = Mapper.map(dL.getTeacher());
        String room = dL.getRoom();
        BLLesson lesson = Mapper.map(dL.getLesson());
        String date = dL.getDate();
        int number = dL.getNumber();
        return new BLDatedLesson(id, group, teacher, room, lesson, date, number);
    }

    public static BLUser map(User u) {
        String login = u.getLogin();
        String name = u.getName();
        Role role = Role.valueOf(u.getRole());
        BLGroup group = null;
        if (u.getGroup() != null)
            group = Mapper.map(u.getGroup());
        return new BLUser(login, name, role, group);
    }

    public static BLLesson map(Lesson l){
        String name = l.getName();
        String description = l.getDescription();
        return new BLLesson(name, description);
    }
}
