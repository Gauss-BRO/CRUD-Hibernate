package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String nameTable = "user_table";
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getDBConnection()) {
            String sql = "CREATE TABLE IF NOT EXISTS " + nameTable +
                    " (id INTEGER not NULL auto_increment, " +
                    " name VARCHAR(255), " +
                    " lastName VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY (id))";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.executeUpdate();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        } catch (SQLException ex) {
            System.out.println("SQLException");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getDBConnection()) {
            String sql = "DROP TABLE IF EXISTS " + nameTable;
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.execute();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        } catch (SQLException ex) {
            System.out.println("SQLException");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getDBConnection()) {
            String sql = "INSERT INTO " + nameTable + " (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, lastName);
            pst.setInt(3, age);
            pst.execute();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        } catch (SQLException ex) {
            System.out.println("SQLException");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getDBConnection()) {
            String sql = "DELETE FROM " + nameTable + " WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, (int) id);
            pst.execute();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        } catch (SQLException ex) {
            System.out.println("SQLException");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getDBConnection()) {
            String sql = "SELECT id, name, lastname, age FROM " + nameTable;
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            while(rs.next()) {
                User user = new User(rs.getString("name"),
                        rs.getString("lastName"),
                        (byte) rs.getInt("age"));
                user.setId((long) rs.getInt("id"));
                userList.add(user);
            }
            rs.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        } catch (SQLException ex) {
            System.out.println("SQLException");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getDBConnection()) {
            String sql = "TRUNCATE TABLE " + nameTable;
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.execute();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        } catch (SQLException ex) {
            System.out.println("SQLException");
        }
    }
}
