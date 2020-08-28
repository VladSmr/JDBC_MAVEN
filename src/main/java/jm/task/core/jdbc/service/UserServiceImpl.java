package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoJDBCImpl();

    // создать таблицу
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    // удалить всю таблицу
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    // сохранить юзера
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
    }

    // удалить юзера по ид
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    // получить всех юзеров
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    // очистить таблицу
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}