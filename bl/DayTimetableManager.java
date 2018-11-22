package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import db.entities.DatedLesson;
import bl.entities.BLDatedLesson;
import db.repositories.DatedLessonRepo;

public class DayTimetableManager {

    private final DatedLessonRepo dLR;

    public DayTimetableManager(DatedLessonRepo dLR){
        this.dLR = dLR;
    }

    public ArrayList<BLDatedLesson> getList(String date){
        ArrayList<DatedLesson> list = dLR.dateList(date);
        ArrayList<BLDatedLesson> blist = new ArrayList<>();
        if (list.size() != 0) {
            for (DatedLesson lesson : list) {
                blist.add(Mapper.map(lesson));
            }
            blist.sort(Comparator.comparing(l -> l.getNumber()));
        }
        return blist;
    }

}
