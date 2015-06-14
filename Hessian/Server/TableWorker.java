import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by ִלטענטי on 10.06.2015.
 */
public class TableWorker implements AccountService{

    DBWorker dbWorker = null;

    //OLD calls for PreparedStatemment
/*
    private final static String GETAMOUNT = "SELECT value FROM users WHERE idUser = ?";
    private final static String ADDAMOUNT = "INSERT INTO users(idUser,value) values(?,?)";

   */
    private final static String GETAMOUNT = "{call GETAMOUNT(?,?)}";
    private final static String ADDAMOUNT = "{call ADDAMOUNT(?,?)}";

    private CallableStatement getStatement;
    private CallableStatement addStatement;

    TableWorker() {
        dbWorker = new DBWorker();
        try {
            getStatement = dbWorker.getDbConnection().prepareCall(GETAMOUNT);
            addStatement = dbWorker.getDbConnection().prepareCall(ADDAMOUNT);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long getAmount(Integer id) {
        Long result = 0L;
        try {
            getStatement.setLong(1, result);
            getStatement.setInt(2, id);
            getStatement.registerOutParameter("val", Types.BIGINT);
            getStatement.execute();
            result = getStatement.getLong("val");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return result;
        }
    }

    public void addAmount(Integer id, Long value) {
        try {
            addStatement.setInt(1, id);
            addStatement.setLong(2, value);
            addStatement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
