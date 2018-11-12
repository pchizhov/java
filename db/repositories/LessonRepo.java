package db.repositories;

import db.Connector;
import db.entities.Lesson;

import java.sql.*;
import java.util.ArrayList;

public class LessonRepo implements IRepository<Lesson> {

    private Connection connection = Connector.getConnection();

    @Override
    public Lesson get(String id) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("SELECT * FROM 'lesson' WHERE name = ?");
            pstmt.setString(1, id);
            ResultSet rS = pstmt.executeQuery();
            while (rS.next()) {
                return new Lesson(rS.getString("name"), rS.getString("description"));
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
                connection.commit();
            }
        }
    }

    @Override
    public ArrayList<Lesson> list() throws SQLException {
        connection.setAutoCommit(false);
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rS = stmt.executeQuery("SELECT * FROM 'lesson'");
            ArrayList<Lesson> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new Lesson(rS.getString("name"), rS.getString("description")));
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
                connection.commit();
            }
        }
    }

    @Override
    public void update(Lesson lesson) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement pstmt = null;
        String sql = "UPDATE 'lesson' SET name=?, description=? WHERE name = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, lesson.getName());
            pstmt.setString(2, lesson.getDescription());
            pstmt.setString(3, lesson.getName());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
        }
        finally {
            if (pstmt != null) {
                pstmt.close();
                connection.commit();
            }
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM 'lesson' where name = ?");
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
        }
        finally {
            if (pstmt != null) {
                pstmt.close();
                connection.commit();
            }
        }
    }
}
