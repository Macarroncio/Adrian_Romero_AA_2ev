package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import  util.Constants.*;

import static util.Constants.*;


public class Database {
    private Connection connection;

    public Connection getConnection() {

            try {
                Class.forName(ORACLE_DRIVER);
                connection = DriverManager.getConnection(ORACLE_URL, USERNAME, PASSWORD);
                System.out.println("Connected!");
            } catch (ClassNotFoundException cnfe) {
                System.out.println("The connection driver could not be loaded. Verify that the drivers are available");
                cnfe.printStackTrace();
            } catch (SQLException sqle) {
                System.out.println("Could not connect to the database server. Check that the data is correct ");
                sqle.printStackTrace();
            }

            return connection;
        }

        public void close() {
            try {
                connection.close();
            } catch (SQLException sqle) {
                System.out.println("Unable to reach server. Please try again");
                sqle.printStackTrace();
            }
        }
    }

