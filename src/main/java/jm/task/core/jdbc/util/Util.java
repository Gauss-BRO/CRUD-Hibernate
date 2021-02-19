package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION =
            "jdbc:mysql://localhost:3306/training_data_base?useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Lasto4kaTo4kaRu";
    private static Connection connection;

    private static SessionFactory sessionFactory;

    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
    }

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            try {
                Properties prop= new Properties();
                prop.setProperty("hibernate.connection.url", DB_CONNECTION);
                prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                prop.setProperty("hibernate.connection.username", DB_USER);
                prop.setProperty("hibernate.connection.password", DB_PASSWORD);
                prop.setProperty("hibernate.connection.driver_class", DB_DRIVER);
                Configuration configuration = new Configuration().addProperties(prop);
                configuration.addAnnotatedClass(User.class);
                sessionFactory = configuration.buildSessionFactory(
                        new StandardServiceRegistryBuilder()
                                .applySettings(prop)
                                .build()
                );
            } catch (Exception e) {
                System.out.println("Exception!" + e);
            }
        }
        return sessionFactory;
    }
    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
