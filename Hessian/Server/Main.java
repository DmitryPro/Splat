import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by ִלטענטי on 10.06.2015.
 */
public class Main {

    public static void main(String[] args) {
        TableWorker tableWorker = new TableWorker();
        //  tableWorker.addAmount(1,new Long(2));
        System.out.println(tableWorker.getAmount(1));
        //  System.out.println(tableWorker.getAmount(2));

    }
}
