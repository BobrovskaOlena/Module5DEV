package com.goit.module4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabasePopulateService {
    public static void main(String[] args) {
        //завдання 3
        Connection connection = H2Database.getInstance().getH2Connection();
        H2Database h2Database = H2Database.getInstance();
        String sqlFilePathPopulate = "src/main/resources/com/goit/module4/sql/populate_db.sql";

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
}
