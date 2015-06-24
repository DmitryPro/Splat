import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/*
Класс сервайса.
После запуска сервера, запускается дополнительный тред с модулем статистики.
 */

public class Service implements AccountService {

    public static final String BINDING_NAME = "sample/Service";
    static DateReq dr = new DateReq();

    public Long getAmount(Integer id) {
        dr.incGetReq();
        return dr.getAmount(id);
    }

    public void addAmount(final Integer id, final Long value) {
        dr.incAddReq();
        dr.addAmount(id, value);

    }



    public static void main(String[] args) throws Exception {
        System.out.println("Start Server");
        final AccountService service = new Service();
        Remote stub = UnicastRemoteObject.exportObject(service, 6969);
        final Registry registry = LocateRegistry.createRegistry(6969);
        registry.bind(BINDING_NAME, stub);
        System.out.println("OK");

        System.out.println("Start StatisticsModule");
        Thread stat = new Thread(new Statistic());
        stat.start();
        System.out.println("OK");
        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}