import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
Здесь происходит подключение к БД
 */

public class DataBaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/DataBase";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private Connection dbConnection = null;

    public DataBaseConnection() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getDbConnection() {
        return dbConnection;
    }


}
