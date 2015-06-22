package RMI.Server;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


/*
Собственно сам сервер.
Здесь мы реализуем многопоточность и отсылку ответа клиенту.
 */

public class Service implements AccountService {

    public static final String BINDING_NAME = "sample/Service";
    AllDataReq adr = new AllDataReq();

    public Long getAmount(Integer id) {
        //return adr.getAmount(id);
        return adr.getAmount(id);
    }

    public void addAmount(Integer id, Long value) {
        //adr.addAmount(id, value);
        adr.addAmount(id, value);
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