package com.javaIntro.JDBCIntro;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AppTest 
{   
    private String connectionString;
    private Connection databaseConnection;
    private Logger logger;
    
    @Before
    public void initialize() {
        this.connectionString = "jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'classpath:users.sql';";
        this.logger = Logger.getLogger("JDBCIntro");

        try {
            this.databaseConnection = DriverManager.getConnection(connectionString);
        } catch(SQLException error) {
            logger.info(error.getMessage());
        }
    }

    @Test
    public void establishH2DatabaseConnection() throws SQLException
    {   
        Boolean isConnectionValid = this.databaseConnection.isValid(0);
        assertEquals(true, isConnectionValid);
    }

    @After
    public void finalize() throws SQLException  {
        this.databaseConnection.close();
    }

    @Test
    public void getUserName() throws SQLException {
            PreparedStatement preparedStatement = this.databaseConnection.prepareStatement("select * from users;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                assertEquals("Churros", name);
            }
    }
}
