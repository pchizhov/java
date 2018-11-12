package db.repositories;

import db.Connector;
import db.entities.Lesson;

import java.sql.*;
import java.util.ArrayList;

public class LessonRepo implements IRepository<Lesson> {

    private Connection connection = Connector.getConnection();

    @Override
    public Lesson get(String id) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement("SELECT * FROM 'lesson' WHERE name = ?");
            pstmt.setString(1, id);
            ResultSet rS = pstmt.executeQuery();
            connection.commit();
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
                connection.setAutoCommit(true);
            }
        }
    }

    @Override
    public ArrayList<Lesson> list() throws SQLException {
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rS = stmt.executeQuery("SELECT * FROM 'lesson'");
            connection.commit();
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
                connection.setAutoCommit(true);
            }
        }
    }

    @Override
    public void update(Lesson lesson) throws SQLException {
        PreparedStatement pstmt = null;
        String sql = "UPDATE 'lesson' SET name=?, description=? WHERE name = ?";
        try {
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, lesson.getName());
            pstmt.setString(2, lesson.getDescription());
            pstmt.setString(3, lesson.getName());
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
            pstmt = connection.prepareStatement("DELETE FROM 'lesson' where name = ?");
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
