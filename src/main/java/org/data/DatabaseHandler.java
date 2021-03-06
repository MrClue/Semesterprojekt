package org.data;

import javafx.collections.FXCollections;
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
    private final String url = "localhost";
    private final int port = 5432;
    private final String databaseName = "ecms";
    private final String username = "postgres";
    private final String password = "password";
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
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM program");
            ResultSet sqlReturnValues = stmt.executeQuery();
            List<Program> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()) {
                returnValue.add(new Program(sqlReturnValues.getInt(1), sqlReturnValues.getString(2)));
            }

            return FXCollections.observableList(returnValue);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public int getProgramID(String productionTitle) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM program " +
                            "WHERE programtitle = ?"
            );
            stmt.setString(1, productionTitle);

            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()) {
                return -1;
            }
            return sqlReturnValues.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean insertProgram(Program program) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO program (programtitle) " +
                            "VALUES (?)"
            );
            stmt.setString(1, program.getTitle());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProgram(int program_ID, String programTitle) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE program " +
                            "SET programtitle = ?" +
                            "WHERE id = ?"
            );
            statement.setString(1, programTitle);
            statement.setInt(2, program_ID);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProgram(int program_ID) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM program " +
                            "WHERE id = ?"
            );
            stmt.setInt(1, program_ID);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Credits> getCredits() {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM credits");
            ResultSet sqlReturnValues = stmt.executeQuery();
            List<Credits> returnValue = new ArrayList<>();
            while (sqlReturnValues.next()) {
                returnValue.add(new Credits(
                        sqlReturnValues.getInt(2),
                        sqlReturnValues.getString(3),
                        sqlReturnValues.getString(4)
                ));
            }
            return FXCollections.observableList(returnValue);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Credits> getProgramCredits(int program_id) {
        List<Credits> listOfCredits = getCredits();
        List<Credits> newListOfCredits = new ArrayList<>();
        for (Credits credit : listOfCredits) {
            if (credit.getProgramID() == program_id) {
                newListOfCredits.add(credit);
            }
        }
        return FXCollections.observableList(newListOfCredits);
    }

    @Override
    public int getCreditID(int program_id, String occupation, String person) {
        try {
            int value = -1;
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM credits " +
                            "WHERE program_id = ? " +
                            "AND occupation = ? " +
                            "AND person = ?"
            );
            stmt.setInt(1, program_id);
            stmt.setString(2, occupation);
            stmt.setString(3, person);

            ResultSet sqlReturnValues = stmt.executeQuery();
            if (sqlReturnValues.next()) {
                value = sqlReturnValues.getInt(1);
            }

            return value;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean insertCredit(Credits credits) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO credits (program_id, occupation, person) " +
                            "VALUES (?, ?, ?)"
            );
            stmt.setInt(1, credits.getProgramID());
            stmt.setString(2, credits.getOccupation());
            stmt.setString(3, credits.getPerson());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCredit(int creditID, String occupation, String person) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE credits " +
                            "SET occupation = ? " +
                            "WHERE id = ?"
            );
            PreparedStatement statement2 = connection.prepareStatement(
                    "UPDATE credits " +
                            "SET person = ? " +
                            "WHERE id = ?"
            );
            statement.setString(1, occupation);
            statement.setInt(2, creditID);
            statement2.setString(1, person);
            statement2.setInt(2, creditID);

            statement.execute();
            statement2.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCredit(int programID, String occupation, String person) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM credits " +
                            "WHERE program_id = ? " +
                            "AND occupation = ? " +
                            "AND person = ?"
            );
            stmt.setInt(1, programID);
            stmt.setString(2, occupation);
            stmt.setString(3, person);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("No matching credit found - see deleteCredit()");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Role getRole(String username) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM login " +
                            "WHERE username = ?"
            );
            stmt.setString(1, username); // all usernames are in lowercase in database
            ResultSet sqlReturnValues = stmt.executeQuery();
            if (!sqlReturnValues.next()) {
                return null;
            }
            return new Role(
                    sqlReturnValues.getString(2),
                    sqlReturnValues.getString(3),
                    sqlReturnValues.getInt(4),
                    sqlReturnValues.getByte(1)
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
