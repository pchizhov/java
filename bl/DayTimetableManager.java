package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import db.entities.DatedLessonDBE;
import bl.entities.DatedLesson;
import db.repositories.DatedLessonRepo;

public class DayTimetableManager {

    private final DatedLessonRepo dLR;

    public DayTimetableManager(DatedLessonRepo dLR){
        this.dLR = dLR;
    }

    public ArrayList<DatedLesson> getList(String date){
        ArrayList<DatedLessonDBE> list = dLR.dateList(date);
        ArrayList<DatedLesson> blist = new ArrayList<>();
        if (list.size() != 0) {
            for (DatedLessonDBE lesson : list) {
                blist.add(Mapper.map(lesson));
            }
            blist.sort(Comparator.comparing(l -> l.getNumber()));
        }
        return blist;
    }

}
