package com.example.zakladybukmacherskie.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DBConnector {

    private final static String DBURL = "jdbc:postgresql://localhost:5432/zaklady";
    private final static String DBUSER = "postgres";
    private final static String DBPASS = "kacper";

    public static Connection connect() {

        Connection connection = null;
        try {
        connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            System.out.println("Połączono");
            log.error("%%%%%%%%%%%%%%%%%%%%%%%%%%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}


