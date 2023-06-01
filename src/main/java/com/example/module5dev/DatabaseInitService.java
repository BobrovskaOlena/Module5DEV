package com.goit.module4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseInitService {
    public static void main(String[] args) throws SQLException {
         //завдання 1
        Connection connection = H2Database.getInstance().getH2Connection();
        //завдання 2
        H2Database h2Database = H2Database.getInstance();
        String sqlFilePathInit = "src/main/resources/com/goit/module4/sql/init_db.sql";

        try (BufferedReader reader = new BufferedReader(new FileReader(sqlFilePathInit))) {
            StringBuilder queryBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                queryBuilder.append(line);
                if (line.trim().endsWith(";")) {
                    String query = queryBuilder.toString();
                    h2Database.executeUpdate(query);
                    queryBuilder.setLength(0);  // Очищуємо буфер
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            H2Database.closeConnection();
        }
    }
}
