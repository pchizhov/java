package db.repositories;

import db.AdvConnection;
import db.Connector;
import db.entities.StudentsResults;

import java.sql.*;
import java.util.ArrayList;

public class StudentsResultsRepo implements IRepository<StudentsResults> {

    private AdvConnection connection = Connector.getConnection();
    private UserRepo uR = new UserRepo();
    private DatedLessonRepo dLR = new DatedLessonRepo();

    @Override
    public StudentsResults get(String id) throws SQLException {
        try {
            ResultSet rS = connection.resultTransaction(() -> {
                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM 'studentsresults' WHERE id = ?");
                pstmt.setString(1, id);
                return pstmt.executeQuery();
            });
            if (rS.next()) {
                return new StudentsResults(rS.getInt("id"), dLR.get(rS.getString("datedlesson")),
                        uR.get(rS.getString("student")),
                        rS.getString("result"));
            }
            return null;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<StudentsResults> list() throws SQLException {
        try {
            ResultSet rS = connection.resultTransaction(() -> {
                Statement stmt = connection.createStatement();
                return stmt.executeQuery("SELECT * FROM 'studentsresults'");
            });
            ArrayList<StudentsResults> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new StudentsResults(rS.getInt("id"), dLR.get(rS.getString("datedlesson")),
                        uR.get(rS.getString("student")),
                        rS.getString("result")));
            }
            return list;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void update(StudentsResults studentsResults) throws SQLException {
        String sql = "UPDATE 'studentsresults' SET id = ?, datedlesson=?, student=?, result=? WHERE id = ?";
        try {
            connection.voidTransaction(() -> {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, studentsResults.getId());
                pstmt.setInt(2, studentsResults.getDatedLesson().getId());
                pstmt.setString(3, studentsResults.getStudent().getLogin());
                pstmt.setString(4, studentsResults.getResult());
                pstmt.setInt(5, studentsResults.getId());
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
                PreparedStatement pstmt = connection.prepareStatement("DELETE FROM 'studentsresults' where id = ?");
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
