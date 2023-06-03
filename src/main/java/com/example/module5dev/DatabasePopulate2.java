package com.example.module5dev;
import java.sql.*;
import java.time.LocalDate;


public class DatabasePopulate2 {
    private static final String INSERT1_STRING = "INSERT INTO worker(id, name, birthday, salary, levels) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT2_STRING = "INSERT INTO client VALUES (?, ?)";
    private static final String INSERT3_STRING = "INSERT INTO project VALUES (?, ?, ?, ?)";
    private static final String INSERT4_STRING = "INSERT INTO project_worker VALUES (?, ?)";
    private PreparedStatement insertStatement1;
    private PreparedStatement insertStatement2;
    private PreparedStatement insertStatement3;
    private PreparedStatement insertStatement4;
    public DatabasePopulate2(Connection connection) {
        try {
            this.insertStatement1 = connection.prepareStatement(INSERT1_STRING);
            this.insertStatement2 = connection.prepareStatement(INSERT2_STRING);
            this.insertStatement3 = connection.prepareStatement(INSERT3_STRING);
            this.insertStatement4 = connection.prepareStatement(INSERT4_STRING);
        } catch(SQLException e) {
            System.out.println("Database Populate construction exception. Reason: " + e.getMessage());
        }
    }
    public void saveWorker(Long id, String name, LocalDate birthday, int salary, String levels) {
        try {
            this.insertStatement1.setLong(1, id);
            this.insertStatement1.setString(2, name);
            this.insertStatement1.setString(3, birthday.toString());
            this.insertStatement1.setInt(4, salary);
            this.insertStatement1.setString(5, levels);
            this.insertStatement1.executeUpdate();
            this.insertStatement1.addBatch();
        } catch(SQLException e) {
            System.out.println("Insert worker exception. Reason: " + e.getMessage());
        }
    }
    public void saveClient(Long id, String name) {
        try {
            this.insertStatement2.setLong(1, id);
            this.insertStatement2.setString(2, name);
            this.insertStatement2.executeUpdate();
            this.insertStatement2.addBatch();
        } catch(SQLException e) {
            System.out.println("Insert client exception. Reason: " + e.getMessage());
        }
    }

    public void saveProject(Long id, Long client_id, LocalDate start_date, LocalDate finish_date) {
        try {
            this.insertStatement3.setLong(1, id);
            this.insertStatement3.setLong(2, client_id);
            this.insertStatement3.setString(3, start_date.toString());
            this.insertStatement3.setString(4, finish_date.toString());
            this.insertStatement3.executeUpdate();
            this.insertStatement3.addBatch();
        } catch(SQLException e) {
            System.out.println("Insert project exception. Reason: " + e.getMessage());
        }
    }

    public void saveProject_worker(Long project_id, Long worker_id) {
        try {
            this.insertStatement4.setLong(1, project_id);
            this.insertStatement4.setLong(2, worker_id);
            this.insertStatement4.executeUpdate();
            this.insertStatement4.addBatch();
        } catch (SQLException e) {
            System.out.println("Insert project_worker exception. Reason: " + e.getMessage());
        }
    }

    public void executeBatchUpdates() {
        try {
            this.insertStatement1.executeBatch();
            this.insertStatement2.executeBatch();
            this.insertStatement3.executeBatch();
            this.insertStatement4.executeBatch();
        } catch (SQLException e) {
            System.out.println("Batch update exception. Reason: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException {

        Connection connection = H2Database.getInstance().getH2Connection();

        DatabasePopulate2 databasePopulate2 = new DatabasePopulate2(connection);


        // INSERT INTO worker
        databasePopulate2.saveWorker(1L, "Anton Skliar", LocalDate.parse("1992-02-05"), 900, "Trainee");
        databasePopulate2.saveWorker(2L, "Mary Jonson", LocalDate.parse("1996-07-05"), 1200, "Junior");
        databasePopulate2.saveWorker(3L, "Daniil Bee", LocalDate.parse("1998-02-21"), 1500, "Middle");
        databasePopulate2.saveWorker(4L, "Olena Bobrovska", LocalDate.parse("1998-07-28"), 2500, "Middle");
        databasePopulate2.saveWorker(5L, "Daniel Ram", LocalDate.parse("1999-06-05"), 3600, "Senior");
        databasePopulate2.saveWorker(6L, "Karina Kostenko", LocalDate.parse("1998-02-22"), 3000, "Middle");
        databasePopulate2.saveWorker(7L, "Oleg Logenko", LocalDate.parse("1996-02-05"), 3100, "Middle");
        databasePopulate2.saveWorker(8L, "Alisa Borysenko", LocalDate.parse("1994-02-10"), 4000, "Senior");
        databasePopulate2.saveWorker(9L, "Tom Kristen", LocalDate.parse("1988-04-23"), 4500, "Senior");
        databasePopulate2.saveWorker(10L, "Olia Kristenko", LocalDate.parse("1983-09-01"), 5500, "Senior");

        // INSERT INTO client
        databasePopulate2.saveClient(1L, "Anna Smith");
        databasePopulate2.saveClient(2L, "Maria Jekson");
        databasePopulate2.saveClient(3L, "Anton Skliar");
        databasePopulate2.saveClient(4L, "Oleg Lagosh");
        databasePopulate2.saveClient(5L, "Viktoria Danysiuk");
        databasePopulate2.saveClient(6L, "Valeriya Rem");

        // INSERT INTO project
        databasePopulate2.saveProject(1L, 1L, LocalDate.parse("2022-01-01"), LocalDate.parse("2022-02-28"));
        databasePopulate2.saveProject(2L, 2L, LocalDate.parse("2021-12-01"), LocalDate.parse("2022-03-31"));
        databasePopulate2.saveProject(3L, 3L, LocalDate.parse("2022-02-15"), LocalDate.parse("2022-06-30"));
        databasePopulate2.saveProject(4L, 4L, LocalDate.parse("2021-11-15"), LocalDate.parse("2022-02-28"));
        databasePopulate2.saveProject(5L, 5L, LocalDate.parse("2022-04-01"), LocalDate.parse("2022-12-31"));
        databasePopulate2.saveProject(6L, 6L, LocalDate.parse("2022-03-01"), LocalDate.parse("2022-05-31"));
        databasePopulate2.saveProject(7L, 1L, LocalDate.parse("2022-07-01"), LocalDate.parse("2023-01-31"));
        databasePopulate2.saveProject(8L, 3L, LocalDate.parse("2022-05-15"), LocalDate.parse("2022-12-31"));
        databasePopulate2.saveProject(9L, 2L, LocalDate.parse("2022-09-01"), LocalDate.parse("2023-05-31"));
        databasePopulate2.saveProject(10L, 5L, LocalDate.parse("2022-08-01"), LocalDate.parse("2022-11-30"));

      // INSERT INTO project_worker
        databasePopulate2.saveProject_worker(1L, 1L);
        databasePopulate2.saveProject_worker(1L, 5L);
        databasePopulate2.saveProject_worker(2L, 2L);
        databasePopulate2.saveProject_worker(2L, 5L);
        databasePopulate2.saveProject_worker(2L, 7L);
        databasePopulate2.saveProject_worker(3L, 1L);
        databasePopulate2.saveProject_worker(3L, 4L);
        databasePopulate2.saveProject_worker(3L, 6L);
        databasePopulate2.saveProject_worker(3L, 7L);
        databasePopulate2.saveProject_worker(4L, 4L);
        databasePopulate2.saveProject_worker(4L, 2L);
        databasePopulate2.saveProject_worker(4L, 7L);
        databasePopulate2.saveProject_worker(5L, 1L);
        databasePopulate2.saveProject_worker(5L, 2L);
        databasePopulate2.saveProject_worker(5L, 8L);
        databasePopulate2.saveProject_worker(5L, 9L);
        databasePopulate2.saveProject_worker(5L, 7L);
        databasePopulate2.saveProject_worker(6L, 8L);
        databasePopulate2.saveProject_worker(6L, 9L);
        databasePopulate2.saveProject_worker(7L, 10L);
        databasePopulate2.saveProject_worker(7L, 1L);
        databasePopulate2.saveProject_worker(7L, 4L);
        databasePopulate2.saveProject_worker(7L, 8L);
        databasePopulate2.saveProject_worker(8L, 8L);
        databasePopulate2.saveProject_worker(8L, 3L);
        databasePopulate2.saveProject_worker(8L, 7L);
        databasePopulate2.saveProject_worker(9L, 2L);
        databasePopulate2.saveProject_worker(9L, 4L);
        databasePopulate2.saveProject_worker(9L, 5L);
        databasePopulate2.saveProject_worker(9L, 8L);
        databasePopulate2.saveProject_worker(10L, 1L);
        databasePopulate2.saveProject_worker(10L, 4L);
        databasePopulate2.saveProject_worker(10L, 5L);
        databasePopulate2.saveProject_worker(10L, 7L);
        databasePopulate2.saveProject_worker(10L, 9L);

        databasePopulate2.executeBatchUpdates();
    }
}