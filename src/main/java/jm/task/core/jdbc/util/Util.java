package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

public class Util {
    static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        Properties properties= new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/usersdb");
        properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "rootroot");
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        sessionFactory = new Configuration().addAnnotatedClass(User.class).addProperties(properties).buildSessionFactory();
        return sessionFactory;
    }
        public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "rootroot");
//            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Not connected");
        }
        return connection;
    }
}
