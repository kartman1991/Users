package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            Statement statement = Util.connect().createStatement();
            statement.execute(
                    "CREATE TABLE `usersdb`.`users` (`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NULL, `lastname` VARCHAR(45) NULL, `age` INT NULL, PRIMARY KEY (`id`));");
            Util.connect().commit();
            statement.close();
            Util.connect().close();
        } catch (SQLException ignore) {
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = Util.connect().createStatement();
            statement.execute("DROP table usersdb.users;");
            Util.connect().commit();
            statement.close();
            Util.connect().close();
        } catch (SQLException ignore) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement statement = Util.connect().prepareStatement(
                    "INSERT INTO `usersdb`.`users` (`name`, `lastname`, `age`) VALUES (?, ?, ?);");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            Util.connect().commit();
            statement.close();
            Util.connect().close();
        } catch (SQLException ignore) {
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement statement = Util.connect().prepareStatement(
                    "DELETE FROM `usersdb`.`users` WHERE (`id` = ?);");
            statement.setLong(1, id);
            statement.executeUpdate();
            Util.connect().commit();
            statement.close();
            Util.connect().close();
        } catch (SQLException ignore) {
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new LinkedList<>();
        ResultSet rs;
        try {
            Statement statement = Util.connect().createStatement();
            rs = statement.executeQuery("SELECT * FROM usersdb.users;");
            while (rs.next()) {
                usersList.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
            }
            Util.connect().commit();
            statement.close();
            Util.connect().close();
        } catch (SQLException ignore) {
        }
        return usersList;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = Util.connect().createStatement();
            statement.execute("TRUNCATE TABLE usersdb.users;");
            Util.connect().commit();
            statement.close();
            Util.connect().close();
        } catch (SQLException ignore) {
        }
    }
}
