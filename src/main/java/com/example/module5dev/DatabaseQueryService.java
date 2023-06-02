package com.example.module5dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseQueryService {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    {
        try {
            connection = H2Database.getInstance().getH2Connection();
            preparedStatement = connection.prepareStatement("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        String sqlFilePath1 = "src/main/resources/com/example/module5dev/find_max_salary_worker.sql";
        List<MaxSalaryWorker> result = new ArrayList<>();
        try (BufferedReader reader1 = new BufferedReader(new FileReader(sqlFilePath1)))
        {
            StringBuilder queryBuilder = new StringBuilder();
            String line;
            while ((line = reader1.readLine()) != null) {
                queryBuilder.append(line);
            }
            String query1 = queryBuilder.toString();
            preparedStatement = connection.prepareStatement(query1);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long ID = resultSet.getLong("ID");
                    String name = resultSet.getString("name");
                    String birthday = resultSet.getString("birthday");
                    String levels = resultSet.getString("levels");
                    int salary = resultSet.getInt(5);
                    MaxSalaryWorker worker1 = new MaxSalaryWorker(ID, name, birthday, levels, salary);
                    result.add(worker1);
                }
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        String sqlFilePath2 = "src/main/resources/com/example/module5dev/find_max_projects_client.sql";
        List<MaxProjectCountClient> result = new ArrayList<>();

        try (BufferedReader reader2 = new BufferedReader(new FileReader(sqlFilePath2)))
              {
                  StringBuilder queryBuilder = new StringBuilder();
                  String line;
                  while ((line = reader2.readLine()) != null) {
                      queryBuilder.append(line);
                  }
                  String query2 = queryBuilder.toString();
                  preparedStatement = connection.prepareStatement(query2);
                  ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int projectCount = resultSet.getInt("project_count");
                MaxProjectCountClient maxProjectCountClient = new MaxProjectCountClient(name, projectCount);
                result.add(maxProjectCountClient);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
    public List<LongestProject> findLongestProject() {
        String sqlFilePath3 = "src/main/resources/com/example/module5dev/find_longest_project.sql";
        List<LongestProject> result = new ArrayList<>();

        try (BufferedReader reader3 = new BufferedReader(new FileReader(sqlFilePath3)))
             {
            StringBuilder queryBuilder = new StringBuilder();
            String line;
            while ((line = reader3.readLine()) != null) {
                queryBuilder.append(line);
            }
            String query3 = queryBuilder.toString();
            preparedStatement=connection.prepareStatement(query3);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                int months = resultSet.getInt("months");
                LongestProject project = new LongestProject(id, months);
                result.add(project);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<YoungerAndOldestWorker> findYoungestAndEldestWorkers() {
        String sqlFilePath4 = "src/main/resources/com/example/module5dev/find_youngest_eldest_workers.sql";
        List<YoungerAndOldestWorker> result = new ArrayList<>();

        try (BufferedReader reader4 = new BufferedReader(new FileReader(sqlFilePath4))) {
            StringBuilder queryBuilder = new StringBuilder();
            String line;
            while ((line = reader4.readLine()) != null) {
                queryBuilder.append(line);
            }
            String query4 = queryBuilder.toString();
            preparedStatement=connection.prepareStatement(query4);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String type = resultSet.getString("TYPE");
                String name = resultSet.getString("name");
                String birthday = resultSet.getString("birthday");
                YoungerAndOldestWorker youngerAndOldestWorker = new YoungerAndOldestWorker(type, name, birthday);
                result.add(youngerAndOldestWorker);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<ProjectPrice> printProjectPrices() {
        String sqlFilePath5 = "src/main/resources/com/example/module5dev/print_project_prices.sql";
        List<ProjectPrice> result = new ArrayList<>();

        try (BufferedReader reader5 = new BufferedReader(new FileReader(sqlFilePath5))) {
            StringBuilder queryBuilder = new StringBuilder();
            String line;
            while ((line = reader5.readLine()) != null) {
                queryBuilder.append(line);
            }
            String query5 = queryBuilder.toString();
            preparedStatement=connection.prepareStatement(query5);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                double projectCost = resultSet.getDouble("project_cost");
                ProjectPrice projectPrice = new ProjectPrice(id, projectCost);
                result.add(projectPrice);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public static void main(String[] args) {
        DatabaseQueryService queryService = new DatabaseQueryService();
        List <MaxSalaryWorker> maxSalaryWorkers= queryService.findMaxSalaryWorker();
        for (MaxSalaryWorker worker : maxSalaryWorkers) {
            System.out.println(worker.toString());
        }
        List<MaxProjectCountClient> maxProjectCountClients = queryService.findMaxProjectsClient();
        System.out.println("Max project count client -> " + maxProjectCountClients.size());
        for (MaxProjectCountClient maxProjectCountClient : maxProjectCountClients) {
            System.out.println("MaxProjectCountClient --> " + maxProjectCountClient.toString());
        }
        List<LongestProject> longestProjects = queryService.findLongestProject();
        for (LongestProject longestProject : longestProjects) {
            System.out.println("Longest projects --> " + longestProject.toString());
        }
        List<YoungerAndOldestWorker> youngerAndOldestWorkers = queryService.findYoungestAndEldestWorkers();
        for (YoungerAndOldestWorker youngerAndOldestWorker : youngerAndOldestWorkers) {
            System.out.println("Younger and oldest workers: --> " + youngerAndOldestWorker.toString());
        }
        List<ProjectPrice> projectPrices = queryService.printProjectPrices();
        for (ProjectPrice projectPrice : projectPrices) {
            System.out.println("Project price is --> " + projectPrice.toString());
        }
    }
}