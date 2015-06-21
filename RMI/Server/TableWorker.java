package Splat.RMI.Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TableWorker implements AccountService {

    DBWorker dbWorker = null;

    private final static String GETAMOUNT = "select val from users where id = ?";
    private final static String ADDAMOUNT = "insert into users set id = ?, val = ? on duplicate key update val = val + ?";

    private PreparedStatement getStatement = null;
    private PreparedStatement addStatement = null;

    TableWorker() {
        dbWorker = new DBWorker();
        try {
            getStatement = dbWorker.getDbConnection().prepareStatement(GETAMOUNT);
            addStatement = dbWorker.getDbConnection().prepareStatement(ADDAMOUNT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long getAmount(Integer id) {
        Long result = 0L;
        try {
            getStatement = dbWorker.getDbConnection().prepareStatement(GETAMOUNT);
            getStatement.setInt(1, id);
            ResultSet res = getStatement.executeQuery();
            res.next();
            result = res.getLong("val");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(0);
        } finally {
            return result;
        }
    }

    public void addAmount(Integer id, Long value) {
        try {
            addStatement = dbWorker.getDbConnection().prepareStatement(ADDAMOUNT);
            addStatement.setInt(1, id);
            addStatement.setLong(2, value);
            addStatement.setLong(3, value);
            addStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
