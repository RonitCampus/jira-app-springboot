package com.jiraapp.repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * Created by matth on 5/15/2017.
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class dbconfig {

    private static Connection connection = null;

    private static boolean isHome = false;

    static {
        try{
            Class.forName("org.postgresql.Driver");
            if(isHome)
                connection = DriverManager.getConnection("jdbc:postgresql://localhost/jira","postgres", "info@999");
            else
                connection = DriverManager.getConnection("jdbc:postgresql://10.10.32.110/jira","postgres", "info@999");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private dbconfig() {
    }

    public static Connection getInstance() throws SQLException {
        if(null!=connection)
            return connection;
        throw new SQLException("Database Initialization failed");
    }
}
