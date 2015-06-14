import java.sql.*;

/**
 * Created by ִלטענטי on 10.06.2015.
 */
public class DBWorker {

    private static final String URL = "jdbc:mysql://localhost:3306/test_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private Connection dbConnection = null;

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

    public Connection getDbConnection() {
        return dbConnection;
    }

    DBWorker() {
        loadClass();
        makeConnection();
    }


}
