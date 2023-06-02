package com.example.module5dev;

import java.sql.*;
import java.util.Objects;

public class H2Database {
    private static final H2Database INSTANCE = new H2Database();
    private static Connection h2Connection;
    private H2Database(){
        try{
            h2Connection = DriverManager.getConnection(Objects.requireNonNull(Property.getConnectionUrlForH2()));
        }catch (SQLException e){
            throw new RuntimeException("Create connection exception");
        }
    }
    public static H2Database getInstance(){
        return INSTANCE;
    }
    public Connection getH2Connection(){
        return h2Connection;
    }
    public int executeUpdate(String query, Object... params) {
        int result;
        try (
                PreparedStatement statement = INSTANCE.getH2Connection().prepareStatement(query)) {
            setParameters(statement,params);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public ResultSet executeQuery(String query,Object... params) {
        try (PreparedStatement statement = INSTANCE.getH2Connection().prepareStatement(query)) {
            setParameters(statement, params);
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setParameters(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 0; params.length>i; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }
    public static void closeConnection(){
        try {
        h2Connection.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
