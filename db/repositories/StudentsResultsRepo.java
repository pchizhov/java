package db.repositories;

import db.Connector;
import db.entities.StudentsResults;

import java.sql.*;
import java.util.ArrayList;

public class StudentsResultsRepo implements IRepository<StudentsResults> {

    private Connection connection = Connector.getConnection();
    private UserRepo uR = new UserRepo();
    private DatedLessonRepo dLR = new DatedLessonRepo();

    @Override
    public StudentsResults get(String id) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("SELECT * FROM 'studentsresults' WHERE id = ?");
            pstmt.setString(1, id);
            ResultSet rS = pstmt.executeQuery();
            while (rS.next()) {
                return new StudentsResults(rS.getInt("id"), dLR.get(rS.getString("datedlesson")),
                        uR.get(rS.getString("student")),
                        rS.getString("result"));
            }
            return null;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    @Override
    public ArrayList<StudentsResults> list() throws SQLException {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rS = stmt.executeQuery("SELECT * FROM 'studentsresults'");
            ArrayList<StudentsResults> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new StudentsResults(rS.getInt("id"), dLR.get(rS.getString("datedlesson")),
                        uR.get(rS.getString("student")),
                        rS.getString("result")));
            }
            return list;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public void update(StudentsResults studentsResults) throws SQLException {
        PreparedStatement pstmt = null;
        String sql = "UPDATE 'studentsresults' SET id = ?, datedlesson=?, student=?, result=? WHERE id = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, studentsResults.getId());
            pstmt.setInt(2, studentsResults.getDatedLesson().getId());
            pstmt.setString(3, studentsResults.getStudent().getLogin());
            pstmt.setString(4, studentsResults.getResult());
            pstmt.setInt(5, studentsResults.getId());
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM 'studentsresults' where id = ?");
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
}
