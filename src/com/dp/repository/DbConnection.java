package com.dp.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    static Connection connection = null;

    private DbConnection(){
    }

    public static Connection getInstance(){
        try{
            if(connection == null){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/diabetes-ds", "postgres", "admin");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
