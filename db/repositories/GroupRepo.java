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
            pstmt = connection.prepareStatement("SELECT * FROM 'group' WHERE number = ?");
            pstmt.setString(1, id);
            ResultSet rS = pstmt.executeQuery();
            while (rS.next()) {
                return new Group(rS.getString("number"), rS.getString("level"));
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
    public ArrayList<Group> list() throws SQLException {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rS = stmt.executeQuery("SELECT * FROM 'group'");
            ArrayList<Group> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new Group(rS.getString("number"), rS.getString("level")));
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
    public void update(Group group) throws SQLException {
        PreparedStatement pstmt = null;
        String sql = "UPDATE 'group' SET number=?, level=? WHERE number = ?";
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, group.getNumber());
            pstmt.setString(2, group.getLevel());
            pstmt.setString(3, group.getNumber());
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
            pstmt = connection.prepareStatement("DELETE FROM 'group' where number = ?");
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
