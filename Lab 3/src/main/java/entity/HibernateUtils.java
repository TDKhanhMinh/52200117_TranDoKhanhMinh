package entity;

import lombok.Getter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HibernateUtils {
    @Getter
    private static SessionFactory sessionFactory;

    static {
        try {
            createDatabaseIfNotExist();
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3307/phoneFactory?useSSL=false&serverTimezone=UTC");

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static void createDatabaseIfNotExist() {
        String jdbcUrl = "jdbc:mysql://localhost:3307/";
        String username = "springstudent";
        String password = "springstudent";
        String dbName = "phoneFactory";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
            System.out.println("Database created or already exists");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Session getSession() throws HibernateException {
        return getSessionFactory().openSession();
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}