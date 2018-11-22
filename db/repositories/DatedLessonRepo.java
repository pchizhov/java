package db.repositories;

import db.AdvConnection;
import db.Connector;
import db.entities.DatedLesson;

import java.sql.*;
import java.util.ArrayList;

public class DatedLessonRepo implements IRepository<DatedLesson> {

    private AdvConnection connection = Connector.getConnection();
    private GroupRepo gR = new GroupRepo();
    private UserRepo uR = new UserRepo();
    private LessonRepo lR = new LessonRepo();

    @Override
    public DatedLesson get(String id) throws SQLException {
        try {
            ResultSet rS = connection.resultTransaction(() -> {
                PreparedStatement p = connection.prepareStatement("SELECT * FROM 'datedlesson' WHERE id = ?");
                p.setString(1, id);
                return p.executeQuery();
            });
            if (rS.next()) {
                return new DatedLesson(rS.getInt("id"), gR.get(rS.getString("group")),
                        uR.get(rS.getString("teacher")),
                        rS.getString("room"), lR.get(rS.getString("lesson")),
                        rS.getString("date"), rS.getInt("number"));
            }
            return null;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<DatedLesson> list() throws SQLException{
        try {
            ResultSet rS = connection.resultTransaction(() -> {
                Statement stmt = connection.createStatement();
                return stmt.executeQuery("SELECT * FROM 'datedlesson'");
            });
            ArrayList<DatedLesson> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new DatedLesson(rS.getInt("id"), gR.get(rS.getString("group")),
                        uR.get(rS.getString("teacher")),
                        rS.getString("room"), lR.get(rS.getString("lesson")),
                        rS.getString("date"), rS.getInt("number")));
            }
            return list;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<DatedLesson> dateList(String date) {
        try {
            ResultSet rS = connection.resultTransaction(() -> {
                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM 'datedlesson' WHERE date = ?");
                pstmt.setString(1, date);
                return pstmt.executeQuery();
            });
            ArrayList<DatedLesson> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new DatedLesson(rS.getInt("id"),
                        gR.get(rS.getString("group")), uR.get(rS.getString("teacher")),
                        rS.getString("room"), lR.get(rS.getString("lesson")),
                        rS.getString("date"), rS.getInt("number")));
            }
            return list;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void update(DatedLesson datedLesson) throws SQLException{
        String sql = "UPDATE 'datedLesson' SET id = ?, group=?, teacher=?, room=?, lesson=?, date=?, number=? WHERE login = ?";
        try {
            connection.voidTransaction(() -> {
                PreparedStatement pstmt = connection.getConnection().prepareStatement(sql);
                pstmt.setInt(1, datedLesson.getId());
                pstmt.setString(2, datedLesson.getGroup().getNumber());
                pstmt.setString(3, datedLesson.getTeacher().getLogin());
                pstmt.setString(4, datedLesson.getRoom());
                pstmt.setString(5, datedLesson.getLesson().getName());
                pstmt.setString(6, datedLesson.getDate());
                pstmt.setInt(7, datedLesson.getNumber());
                pstmt.setInt(8, datedLesson.getId());
                pstmt.executeUpdate();
                return null;
            });
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        try {
            connection.voidTransaction(() -> {
                PreparedStatement pstmt = connection.getConnection().prepareStatement("DELETE FROM 'datedlesson' where id = ?");
                pstmt.setString(1, id);
                pstmt.executeUpdate();
                return null;
            });
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
