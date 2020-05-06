package org.data;

import java.sql.*;

public class DatabaseHandler {
    private static DatabaseHandler instance;
    private String url = "";
    private int port = 5432;
    private String databaseName = "database_test";
    private String username = "postgres";
    private String password = "hejhej123";
    private Connection connection = null;

    private DatabaseHandler() {
        initializePostgresqlDatabase();
    }

    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    private void initializePostgresqlDatabase() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) System.exit(-1);
        }
    }

    public boolean createUser(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, cpr) VALUES (?,?)");
            preparedStatement.setString(1, "kalb");
            preparedStatement.setString(2, "1111111111");
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
