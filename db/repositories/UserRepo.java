package db.repositories;

import db.AdvConnection;
import db.Connector;
import db.entities.UserDBE;

import java.sql.*;
import java.util.ArrayList;

public class UserRepo implements IRepository<UserDBE> {

    private AdvConnection connection = Connector.getConnection();
    private GroupRepo gR = new GroupRepo();

    @Override
    public UserDBE get(String id) throws SQLException {
        try {
            ResultSet rS = connection.resultTransaction(() -> {
                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM 'user' where login = ?");
                pstmt.setString(1, id);
                return pstmt.executeQuery();
            });
            if(rS.next()) {
                return new UserDBE(rS.getString("login"), rS.getString("password"),
                        rS.getString("name"), rS.getString("role"),
                        gR.get(rS.getString("group")));
            }
            return null;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<UserDBE> list() throws SQLException {
        try {
            ResultSet rS = connection.resultTransaction(() -> {
                Statement stmt = connection.createStatement();
                return stmt.executeQuery("SELECT * FROM 'user'");
            });
            ArrayList<UserDBE> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new UserDBE(rS.getString("login"), rS.getString("password"),
                        rS.getString("name"), rS.getString("role"),
                        gR.get(rS.getString("group"))));
            }
            return list;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void update(UserDBE user) throws SQLException {
        String sql = "UPDATE 'user' SET login=?, password=?, name=?, role=?, group=? WHERE login = ?";
        try {
            connection.voidTransaction(() -> {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, user.getLogin());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getName());
                pstmt.setString(4, user.getRole());
                if (user.getGroup().getNumber() != null)
                    pstmt.setString(5, user.getGroup().getNumber());
                else
                    pstmt.setString(5, "NULL");
                pstmt.setString(6, user.getLogin());
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
                PreparedStatement pstmt = connection.prepareStatement("DELETE FROM 'user' where login = ?");
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
