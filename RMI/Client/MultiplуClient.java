package Splat.RMI.Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

/**
 * Created by marsik on 21.06.15.
 */
public class MultiplуClient  {

    public static final String BINDING_NAME = "sample/Service";

    public static void main(String[] args) throws Exception{
        Thread[] threads = new Thread[2];
        for (int i = 0; i < threads.length ; i++) {
            if (i < threads.length/2)
                threads[i] = new Thread(new GetThread());
            else
                threads[i] = new Thread(new AddThread());
        }
        startThreads(threads);


    }

    private static void startThreads(Thread[] threads){
        for (Thread t: threads)
            t.start();
    }

    static class AddThread implements Runnable {
        public void run(){
            for(int i = 0; i < 1; i++) {
                try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 6969);
                    AccountService service = (AccountService) registry.lookup(BINDING_NAME);
                    Random rand = new Random();
                    System.out.println("addoperation " + rand.nextInt(25) + 1 + " " + rand.nextLong() % 10000000);
                    service.addAmount(rand.nextInt(25) + 1, rand.nextLong() % 10000000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    static class GetThread implements Runnable {
        public void run(){
            for(int i = 0; i < 1; i++) {
                try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 6969);
                    AccountService service = (AccountService) registry.lookup("Service");
                    Random rand = new Random();
                    System.out.println(service.getAmount(rand.nextInt(25) + 1));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
