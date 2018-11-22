package db.repositories;

import db.AdvConnection;
import db.Connector;
import db.entities.Lesson;

import java.sql.*;
import java.util.ArrayList;

public class LessonRepo implements IRepository<Lesson> {

    private AdvConnection connection = Connector.getConnection();

    @Override
    public Lesson get(String id) throws SQLException {
        try {
            ResultSet rS = connection.resultTransaction(() -> {
                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM 'lesson' WHERE name = ?");
                pstmt.setString(1, id);
                return pstmt.executeQuery();
            });
            if (rS.next()) {
                return new Lesson(rS.getString("name"), rS.getString("description"));
            }
            return null;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Lesson> list() throws SQLException {
        try {
            ResultSet rS = connection.resultTransaction(() -> {
                Statement stmt = connection.createStatement();
                return stmt.executeQuery("SELECT * FROM 'lesson'");
            });
            ArrayList<Lesson> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new Lesson(rS.getString("name"), rS.getString("description")));
            }
            return list;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void update(Lesson lesson) throws SQLException {
        String sql = "UPDATE 'lesson' SET name=?, description=? WHERE name = ?";
        try {
            connection.voidTransaction(() -> {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, lesson.getName());
                pstmt.setString(2, lesson.getDescription());
                pstmt.setString(3, lesson.getName());
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
                PreparedStatement pstmt = connection.prepareStatement("DELETE FROM 'lesson' where name = ?");
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
