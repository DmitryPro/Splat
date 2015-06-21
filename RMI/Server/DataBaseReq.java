package RMI.Server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataBaseReq implements AccountService {

    DataBaseConnection dbc;

    private final static String getRq = "select val from users where id = ?";
    private final static String addRq = "insert into users set id = ?, val = ? on duplicate key update val = val + ?";

    private PreparedStatement get = null;
    private PreparedStatement add = null;

    DataBaseReq() {
        dbc = new DataBaseConnection();
        try {
            get = dbc.getDbConnection().prepareStatement(getRq);
            add = dbc.getDbConnection().prepareStatement(addRq);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long getAmount(Integer id) {
        Long result = 0L;
        try {
            get = dbc.getDbConnection().prepareStatement(getRq);
            get.setInt(1, id);
            ResultSet res = get.executeQuery();
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
            add = dbc.getDbConnection().prepareStatement(addRq);
            add.setInt(1, id);
            add.setLong(2, value);
            add.setLong(3, value);
            add.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
