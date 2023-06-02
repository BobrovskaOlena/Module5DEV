package com.example.module5dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabasePopulate2 {
    private static final String INSERT1_STRING = "INSERT INTO worker VALUES (?, ?, ?, ?, ?)";
    //worker (id, name,birthday, salary, levels)
    private static final String INSERT2_STRING = "INSERT INTO client VALUES (?, ?)";
    //client (id, name)
    private static final String INSERT3_STRING = "INSERT INTO project VALUES (?, ?, ?, ?)";
    //project (id, client_id, start_date, finish_date)
    private static final String INSERT4_STRING = "INSERT INTO project_worker VALUES (?, ?)";
    //project_worker (project_id, worker_id)
    private  Connection connection;
    private PreparedStatement insertStatement1;
    private PreparedStatement insertStatement2;
    private PreparedStatement insertStatement3;
    private PreparedStatement insertStatement4;
    public DatabasePopulate2(Connection connection) throws SQLException, IOException {
        this.connection = connection;
        try {
            this.insertStatement1 = connection.prepareStatement(INSERT1_STRING);
            this.insertStatement2 = connection.prepareStatement(INSERT2_STRING);
            this.insertStatement3 = connection.prepareStatement(INSERT3_STRING);
            this.insertStatement4 = connection.prepareStatement(INSERT4_STRING);
            } catch(SQLException e) {
            System.out.println("Database Populate construction exception. Reason: " + e.getMessage());
        }
    }
    public int saveWorker(Long id, String name, LocalDate birthday, int salary, String levels) {
        try {
            this.insertStatement1.setLong(1, id);
            this.insertStatement1.setString(2, name);
            this.insertStatement1.setDate(3, java.sql.Date.valueOf(birthday.toString()));
            this.insertStatement1.setInt(4, salary);
            this.insertStatement1.setString(5, levels);
            return this.insertStatement1.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Insert worker exception. Reason: " + e.getMessage());
            return -1;
        }
    }
    public int saveClient (Long id, String name){
        try {
            this.insertStatement2.setLong(1, id);
            this.insertStatement2.setString(2, name);
            return this.insertStatement2.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Insert client exception. Reason: " + e.getMessage());
            return -1;
        }
    }
    public int saveProject(Long id, Long client_id, LocalDate start_date, LocalDate finish_date){
        try {
            this.insertStatement3.setLong(1, id);
            this.insertStatement3.setLong(2, client_id);
            this.insertStatement3.setDate(3, java.sql.Date.valueOf(start_date.toString()));
            this.insertStatement3.setDate(4, java.sql.Date.valueOf(finish_date.toString()));
            return this.insertStatement3.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Insert project exception. Reason: " + e.getMessage());
            return -1;
        }
    }
    public int saveProject_worker(Long project_id, Long worker_id) {
        try {
            this.insertStatement4.setLong(1, project_id);
            this.insertStatement4.setLong(2, worker_id);
            return this.insertStatement3.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert project_worker exception. Reason: " + e.getMessage());
            return -1;
        }
    }}
       /* Connection connection = H2Database.getInstance().getH2Connection();
        H2Database h2Database = H2Database.getInstance();
        String sqlFilePathPopulate = "src/main/resources/com/example/module5dev/populate_db.sql";

        try (BufferedReader reader = new BufferedReader(new FileReader(sqlFilePathPopulate))) {
            StringBuilder queryBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                queryBuilder.append(line);
                if (line.trim().endsWith(";")) {
                    String query = queryBuilder.toString();
                    connection.createStatement().executeUpdate(query);
                    queryBuilder.setLength(0);
                }
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            H2Database.closeConnection();

        }
    }
}*/


