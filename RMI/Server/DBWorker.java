package Splat.RMI.Server;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
Здесь происходит подключение к БД
 */

public class DBWorker {

    private static final String URL = "jdbc:mysql://localhost:3306/DataBase";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
   // private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private Connection dbConnection = null;

    public DBWorker(){
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            dbConnection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*
    private void loadClass() {
        try {
            Class.forName(DB_DRIVER);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Can't load jdbc mysql driver");
            e.printStackTrace();
        }
    }

    private void makeConnection() {
        try {
            dbConnection = DriverManager.getConnection(URL,USER,PASSWORD);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

     */
    public Connection getDbConnection() {
        return dbConnection;
    }



}
