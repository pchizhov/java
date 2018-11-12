package db.repositories;

import db.entities.Group;
import db.Connector;

import java.util.ArrayList;
import java.sql.*;

public class GroupRepo implements IRepository<Group> {

    private Connection connection = Connector.getConnection();

    @Override
    public Group get(String id) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement("SELECT * FROM 'group' WHERE number = ?");
            pstmt.setString(1, id);
            ResultSet rS = pstmt.executeQuery();
            connection.commit();
            while (rS.next()) {
                return new Group(rS.getString("number"), rS.getString("level"));
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
    public ArrayList<Group> list() throws SQLException {
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rS = stmt.executeQuery("SELECT * FROM 'group'");
            connection.commit();
            ArrayList<Group> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new Group(rS.getString("number"), rS.getString("level")));
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
    public void update(Group group) throws SQLException {
        PreparedStatement pstmt = null;
        String sql = "UPDATE 'group' SET number=?, level=? WHERE number = ?";
        try {
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, group.getNumber());
            pstmt.setString(2, group.getLevel());
            pstmt.setString(3, group.getNumber());
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
            pstmt = connection.prepareStatement("DELETE FROM 'group' where number = ?");
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
