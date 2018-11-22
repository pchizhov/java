package db.repositories;

import db.AdvConnection;
import db.entities.Group;
import db.Connector;

import java.util.ArrayList;
import java.sql.*;

public class GroupRepo implements IRepository<Group> {

    private AdvConnection connection = Connector.getConnection();

    @Override
    public Group get(String id) throws SQLException {
        try {
            ResultSet rS = connection.resultTransaction(() -> {
                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM 'group' WHERE number = ?");
                pstmt.setString(1, id);
                return pstmt.executeQuery();
            });
            if (rS.next()) {
                return new Group(rS.getString("number"), rS.getString("level"));
            }
            return null;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Group> list() throws SQLException {
        try {
            ResultSet rS = connection.resultTransaction(() -> {
                Statement stmt = connection.createStatement();
                return stmt.executeQuery("SELECT * FROM 'group'");
            });
            ArrayList<Group> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new Group(rS.getString("number"), rS.getString("level")));
            }
            return list;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void update(Group group) throws SQLException {
        String sql = "UPDATE 'group' SET number=?, level=? WHERE number = ?";
        try {
            connection.voidTransaction(() -> {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, group.getNumber());
                pstmt.setString(2, group.getLevel());
                pstmt.setString(3, group.getNumber());
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
                PreparedStatement pstmt = connection.prepareStatement("DELETE FROM 'group' where number = ?");
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
