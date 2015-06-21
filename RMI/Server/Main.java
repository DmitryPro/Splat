package Splat.RMI.Server;


public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/DataBase";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private final static String GETAMOUNT = "select val from users where id = ?";
    private final static String ADDAMOUNT = "insert into users set id = ?, val = ? on duplicate key update val = val + ?";
    static int serverDelay = 0;
    static boolean DEBUG = false;
    static boolean KILL = false;

    public static void main(String[] args) {
        TableWorker tb = new TableWorker();
        System.out.println(tb.getAmount(15));





        /*

        cash.push(7, 777l);
        System.out.println(cash.getTime(6));

         TableWorker tb = new TableWorker();
        //tb.addAmount(15, 15L);
        System.out.println(tb.getAmount(14));
         */




        /*
        Connection connection = null;
        PreparedStatement addStatement = null;
        PreparedStatement getStatement = null;
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            getStatement = connection.prepareStatement(GETAMOUNT);

            ResultSet res = getStatement.executeQuery();
            //String ans = "";
            //Long val = res.getLong("id");
            res.next();
            Long result = res.getLong("val");
            System.out.println(result);

        }catch (SQLException e){
            e.printStackTrace();
        }
         */


/*
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            addStatement = connection.prepareStatement(ADDAMOUNT);
            addStatement.setInt(1, 12);
            addStatement.setLong(2, 145L);
            addStatement.setLong(3, 145L);

            addStatement.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
*/
    }
}