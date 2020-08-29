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
        PreparedStatement ps = null;
        String sql = "CREATE TABLE User (id BIGINT NOT NULL," +
                "name VARCHAR(45) NOT NULL," +
                "lastName VARCHAR(45) NOT NULL," +
                "age INT NOT NULL," +
                "PRIMARY KEY (id))";
        try {
            ps = connection.prepareStatement(sql);
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
        PreparedStatement statement = null;
        String sql = "DROP TABLE User";
        try {
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Table was deleted successfully");
        } catch (SQLException e) {
            System.out.println("***** Got sqlException deleting table:");
            e.printStackTrace();
        }
    }

    // сохранить юзера
    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement ps = null;
        Random random = new Random();
        String sql = "INSERT INTO User (id, name, lastName, age) VALUES (?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
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
        PreparedStatement ps = null;
        String sql = "DELETE FROM User WHERE id=?";
        try {
            ps = connection.prepareStatement(sql);
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
        String sql = "SELECT id, name, lastName, age FROM User";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
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
        PreparedStatement ps = null;
        String sql = "DELETE FROM User";
        try {
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Table was cleaned successfully");
        } catch (SQLException e) {
            System.out.println("***** Got sqlException removing user:");
            e.printStackTrace();
        }
    }
}