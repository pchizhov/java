package bl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import db.entities.DatedLesson;
import bl.entities.BLDatedLesson;
import db.repositories.DatedLessonRepo;

public class DayTimetableManager {

    final DatedLessonRepo dLR;

    public DayTimetableManager(DatedLessonRepo dLR){
        this.dLR = dLR;
    }

    public ArrayList<BLDatedLesson> getList(String date){
        ArrayList<DatedLesson> list = null;
        try {
            list = dLR.dateList(date);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<BLDatedLesson> blist = new ArrayList<>();
        for (DatedLesson lesson : list) {
            blist.add(Mapper.map(lesson));
        }
        if (blist.size() != 0)
            blist.sort(Comparator.comparing(l -> l.getNumber()));
        return blist;
    }

}
