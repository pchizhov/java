package db.repositories;

import db.Connector;
import db.entities.DatedLesson;

import java.sql.*;
import java.util.ArrayList;

public class DatedLessonRepo implements IRepository<DatedLesson> {

    private Connection connection = Connector.getConnection();
    private GroupRepo gR = new GroupRepo();
    private UserRepo uR = new UserRepo();
    private LessonRepo lR = new LessonRepo();

    @Override
    public DatedLesson get(String id) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement("SELECT * FROM 'datedlesson' WHERE id = ?");
            pstmt.setString(1, id);
            ResultSet rS = pstmt.executeQuery();
            connection.commit();
            while (rS.next()) {
                return new DatedLesson(rS.getInt("id"), gR.get(rS.getString("group")),
                        uR.get(rS.getString("teacher")),
                        rS.getString("room"), lR.get(rS.getString("lesson")),
                        rS.getString("date"), rS.getInt("number"));
            }
            return null;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
            return null;
        }
        finally {
            if (pstmt != null) {
                pstmt.close();
                connection.setAutoCommit(true);
            }
        }
    }

    @Override
    public ArrayList<DatedLesson> list() throws SQLException{
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rS = stmt.executeQuery("SELECT * FROM 'datedlesson'");
            connection.commit();
            ArrayList<DatedLesson> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new DatedLesson(rS.getInt("id"), gR.get(rS.getString("group")),
                        uR.get(rS.getString("teacher")),
                        rS.getString("room"), lR.get(rS.getString("lesson")),
                        rS.getString("date"), rS.getInt("number")));
            }
            return list;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
            return null;
        }
        finally {
            if (stmt != null) {
                stmt.close();
                connection.setAutoCommit(true);
            }
        }
    }

    public ArrayList<DatedLesson> dateList(String date) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement("SELECT * FROM 'datedlesson' WHERE date = ?");
            pstmt.setString(1, date);
            ResultSet rS = pstmt.executeQuery();
            connection.commit();
            ArrayList<DatedLesson> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new DatedLesson(rS.getInt("id"),
                        gR.get(rS.getString("group")), uR.get(rS.getString("teacher")),
                        rS.getString("room"), lR.get(rS.getString("lesson")),
                        rS.getString("date"), rS.getInt("number")));
            }
            return list;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
            return null;
        }
        finally {
            if (pstmt != null) {
                pstmt.close();
                connection.setAutoCommit(true);
            }
        }
    }

    @Override
    public void update(DatedLesson datedLesson) throws SQLException{
        PreparedStatement pstmt = null;
        String sql = "UPDATE 'datedLesson' SET id = ?, group=?, teacher=?, room=?, lesson=?, date=?, number=? WHERE login = ?";
        try {
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, datedLesson.getId());
            pstmt.setString(2, datedLesson.getGroup().getNumber());
            pstmt.setString(3, datedLesson.getTeacher().getLogin());
            pstmt.setString(4, datedLesson.getRoom());
            pstmt.setString(5, datedLesson.getLesson().getName());
            pstmt.setString(6, datedLesson.getDate());
            pstmt.setInt(7, datedLesson.getNumber());
            pstmt.setInt(8, datedLesson.getId());
            pstmt.executeUpdate();
            connection.commit();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
        }
        finally {
            if (pstmt != null) {
                pstmt.close();
                connection.setAutoCommit(true);
            }
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement("DELETE FROM 'datedlesson' where id = ?");
            pstmt.setString(1, id);
            pstmt.executeUpdate();
            connection.commit();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
        }
        finally {
            if (pstmt != null) {
                pstmt.close();
                connection.setAutoCommit(true);
            }
        }
    }
}
