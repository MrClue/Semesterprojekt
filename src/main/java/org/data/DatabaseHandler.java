package org.data;

import org.application.Credits;
import org.application.IDatabaseHandler;
import org.application.Program;

import org.application.ILogin;
import org.application.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler implements IDatabaseHandler, ILogin {
    private static DatabaseHandler instance;
    private String url = "localhost";
    private int port = 5432;
    private String databaseName = "ecms";
    private String username = "postgres";
    private String password = "password";
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

    @Override
    public List<Program> getPrograms() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM programs");
            ResultSet sqlReturnValues = stmt.executeQuery();
            List<Program> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()) {
                //returnValue.add(new Program(sqlReturnValues.getInt(1), sqlReturnValues.getString(2), sqlReturnValues.getCredit()));
            }
            return returnValue;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int getProgramID(String productionTitle) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM programs WHERE programtitle = ?");
            stmt.setString(1, productionTitle);

            ResultSet sqlReturnValues = stmt.executeQuery();

            return sqlReturnValues.getInt(1);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean createProgram(Program program) {
        return false;
    }

    @Override
    public boolean updateProgram() {
        return false;
    }

    @Override
    public boolean deleteProgram() {
        return false;
    }

    @Override
    public List<Credits> getCredits() {
        return null;
    }

    @Override
    public Credits getCredit(int id) {
        return null;
    }

    @Override
    public boolean createCredit(Credits credit) {
        return false;
    }

    @Override
    public boolean updateCredit() {
        return false;
    }

    @Override
    public boolean deleteCredit() {
        return false;
    }

    @Override
    public Role getRole(String username) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM login WHERE username = ?");
            stmt.setString(1, username);
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()) {
                return null;
            }
            return new Role(sqlReturnValues.getString(2), sqlReturnValues.getString(3), sqlReturnValues.getInt(4),sqlReturnValues.getByte(1));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /*public boolean createUser(){
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
    }*/
}
