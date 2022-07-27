package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Statement statement;

    {
        try {
            statement = Util.connect().createStatement();
        } catch (SQLException e) {
            System.out.println("Коннект null? проверь");
        }
    }

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try {
            statement.execute("CREATE TABLE `usersdb`.`users` (`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NULL, `lastname` VARCHAR(45) NULL, `age` INT NULL, PRIMARY KEY (`id`));");
        } catch (SQLException ignore) {
        }
    }

    public void dropUsersTable() {
        try {
            statement.execute("DROP table usersdb.users;");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement.execute("INSERT INTO `usersdb`.`users` (`name`, `lastname`, `age`) VALUES ('" + name + "', '" + lastName + "', '" + age + "');");
        } catch (SQLException ignore) {
        }
    }

    public void removeUserById(long id) {
        try {
            statement.execute("DELETE FROM `usersdb`.`users` WHERE (`id` = '" + id + "');");
        } catch (SQLException ignore) {
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new LinkedList<>();
        ResultSet rs;
        try {
            rs = statement.executeQuery("SELECT * FROM usersdb.users;");
            while (rs.next()) {
                usersList.add(new User(rs.getString(2), rs.getString(3), rs.getByte(4)));
            }
        } catch (SQLException ignore) {
        }
        return usersList;
    }

    public void cleanUsersTable() {
        try {
            statement.execute("TRUNCATE TABLE usersdb.users;");
        } catch (SQLException ignore) {
        }
    }
}
