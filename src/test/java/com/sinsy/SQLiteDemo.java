package com.sinsy;

import java.sql.*;

public class SQLiteDemo {

    public static void main(String[] args) throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:/db/database.db");

        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, age INT NOT NULL)");
        statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Alice', 28)");
        statement.executeUpdate("INSERT INTO users (name, age) VALUES ('Bob', 32)");

        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") + " " + resultSet.getInt("age"));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

}
