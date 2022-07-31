package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }
    @Override
    public void createUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            NativeQuery query = session.createSQLQuery("CREATE TABLE `usersdb`.`users` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NULL,`lastName` VARCHAR(45) NULL,`age` INT NULL,PRIMARY KEY (`id`),UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception ignore) {}
    }
    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            NativeQuery query = session.createSQLQuery("DROP TABLE IF EXISTS users");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception ignore) {}
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            User user = new User(name, lastName, age);
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ignore) {}
    }
    @Override
    public void removeUserById(long id) {
        try {
            User user = new User();
            user.setId(id);
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            session.close();
        } catch (Exception ignore) {}
    }
    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            users = (List<User>) session.createQuery("From User").list();
            session.close();
        } catch (Exception ignore) {}
        return users;
    }
    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception ignore) {}
    }
}
