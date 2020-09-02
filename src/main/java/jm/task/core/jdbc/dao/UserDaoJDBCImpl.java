package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDaoJDBCImpl extends Util implements UserDao {

    private Connection connection = getConnection();

    public UserDaoJDBCImpl() {
    }

    // создать таблицу
    public void createUsersTable() {
        try (PreparedStatement ps = connection.prepareStatement("CREATE TABLE User (id BIGINT NOT NULL," +
                "name VARCHAR(45) NOT NULL," +
                "lastName VARCHAR(45) NOT NULL," +
                "age INT NOT NULL," +
                "PRIMARY KEY (id))")) {
            ps.executeUpdate();
            System.out.println("Table was created successfully");
        } catch (SQLException e) {
            System.out.println("***** Got sqlException creating new users table:");
            e.printStackTrace();
        } catch (NullPointerException eN) {
            System.out.println("***** Got NullPointerException creating new users table:");
            eN.printStackTrace();
        }
    }

    // удалить всю таблицу
    public void dropUsersTable() {
        try (PreparedStatement ps = connection.prepareStatement("DROP TABLE User")) {
            ps.executeUpdate();
            System.out.println("Table was deleted successfully");
        } catch (SQLException e) {
            System.out.println("***** Got sqlException deleting table:");
            e.printStackTrace();
        }
    }

    // сохранить юзера
    public void saveUser(String name, String lastName, byte age) {
        Random random = new Random();
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO User (id, name, lastName, age)" +
                " VALUES (?, ?, ?, ?)")) {
            ps.setLong(1, random.nextLong());
            ps.setString(2, name);
            ps.setString(3, lastName);
            ps.setByte(4, age);
            ps.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("***** Got sqlException saving user:");
            e.printStackTrace();
        }
    }

    // удалить юзера по ид
    public void removeUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM User WHERE id=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("User with id - " + id + " was deleted successfully");
        } catch (SQLException e) {
            System.out.println("***** Got sqlException removing user:");
            e.printStackTrace();
        }
    }

    // получить всех юзеров
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (Statement s = connection.createStatement()) {
            ResultSet resultSet = s.executeQuery("SELECT id, name, lastName, age FROM User");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            System.out.println("***** Got sqlException getting all users:");
            e.printStackTrace();
        }
        return allUsers;
    }

    // очистить таблицу
    public void cleanUsersTable() {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM User")) {
            ps.executeUpdate();
            System.out.println("Table was cleaned successfully");
        } catch (SQLException e) {
            System.out.println("***** Got sqlException removing user:");
            e.printStackTrace();
        }
    }
}