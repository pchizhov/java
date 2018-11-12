package db.repositories;

import db.Connector;
import db.entities.User;

import java.sql.*;
import java.util.ArrayList;

public class UserRepo implements IRepository<User> {

    private Connection connection = Connector.getConnection();
    private GroupRepo gR = new GroupRepo();

    @Override
    public User get(String id) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement("SELECT * FROM 'user' where login = ?");
            pstmt.setString(1, id);
            ResultSet rS = pstmt.executeQuery();
            connection.commit();
            while (rS.next()) {
                return new User(rS.getString("login"), rS.getString("password"),
                        rS.getString("name"), rS.getString("role"),
                        gR.get(rS.getString("group")));
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
    public ArrayList<User> list() throws SQLException {
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            ResultSet rS = stmt.executeQuery("SELECT * FROM 'user'");
            connection.commit();
            ArrayList<User> list = new ArrayList<>();
            while (rS.next()) {
                list.add(new User(rS.getString("login"), rS.getString("password"),
                        rS.getString("name"), rS.getString("role"),
                        gR.get(rS.getString("group"))));
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
    public void update(User user) throws SQLException {
        PreparedStatement pstmt = null;
        String sql = "UPDATE 'user' SET login=?, password=?, name=?, role=?, group=? WHERE login = ?";
        try {
            connection.setAutoCommit(false);
            pstmt = connection.prepareStatement(sql);
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
            pstmt = connection.prepareStatement("DELETE FROM 'user' where login = ?");
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
