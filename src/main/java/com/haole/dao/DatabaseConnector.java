package com.haole.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private static final String PROPERTIES_FILE = "db.properties";
    private static String url, username, password;

    static {
        Properties properties = new Properties();
        try (InputStream inputStream = DatabaseConnector.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)){

            properties.load(inputStream);
            // get environment first to check if the program is running on docker or local machine
            // if run on docker -> environment will be set -> overwrite info inside db.properties
            url = System.getenv().getOrDefault("DB_URL", properties.getProperty("DB.URL"));
            username = System.getenv().getOrDefault("DB_USER", properties.getProperty("DB.USERNAME"));
            password = System.getenv().getOrDefault("DB_PASSWORD", properties.getProperty("DB.PASSWORD"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}