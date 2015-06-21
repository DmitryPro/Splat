package Splat.RMI.Server;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/*
Собственно сам сервер.
Здесь мы реализуем многопоточность и отсылку ответа клиенту.
 */

public class Service implements AccountService {

    public static final String BINDING_NAME = "sample/HelloService";
    TableWorker tb = new TableWorker();
    //CacheWorker cash = new CacheWorker();

    public synchronized Long getAmount(Integer id) {
        Long ans = tb.getAmount(id);
        return ans;
    }

    public synchronized void addAmount(Integer id, Long value) {
        tb.addAmount(id, value);
    }


    public static void main(String[] args) throws Exception {
        final Registry registry = LocateRegistry.createRegistry(6969);
        System.out.println(" OK");

        final AccountService service = new Service();
        Remote stub = UnicastRemoteObject.exportObject(service, 6969);

        System.out.print("Binding service...");
        registry.bind(BINDING_NAME, stub);
        System.out.println(" OK");
        System.out.print("Starting registry...");
        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}