import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.Scanner;

/*
Параметры задаются при запуску
args[0] - количество потоков на чтение
args[1] - количество потоков на запись
Далее если нам надо потоки взаимодействовали id из диапазона,
то мы задаем записываем в args[3] два числа через тире,
где числа это начало и конец диапазона.
Если же нам надо надо взаимодействовать с конкретными id значениями
то мы просто в аргументах передаем эти id.
 */

public class MultiplyClient {

    public static final String BINDING_NAME = "sample/Service";
    public static int[] mas;

    public static void main(String[] args) throws Exception {
        int rCount = Integer.parseInt(args[0]);
        int wCount = Integer.parseInt(args[1]);
        if (args.length == 3 && args[2].contains(" - ")) {
            Scanner sc = new Scanner(args[2]);
            int left = sc.nextInt();
            sc.next();
            int right = sc.nextInt();
            mas = new int[right - left + 1];
            int i = 0;
            while ((left + i) != right + 1) {
                mas[i] = left + i;
                i++;
            }
        }
        else{
            mas = new int[args.length - 2];
            for (int i = 0; i < mas.length; i++) {
                mas[i] = Integer.parseInt(args[i + 2]);
            }
        }

        Thread[] threads = new Thread[rCount + wCount];
        for (int i = 0; i < rCount; i++){
            threads[i] = new Thread(new GetThread());
        }
        for (int i = rCount; i < wCount + rCount; i++) {
            threads[i] = new Thread(new AddThread());
        }
        System.out.println("Starting");
        startThreads(threads);
    }

    private static void startThreads(Thread[] threads) {
        for (Thread t : threads)
            t.start();
    }




    static class AddThread implements Runnable {
        public void run() {
            while(true) {
                try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 6969);
                    AccountService service = (AccountService) registry.lookup(BINDING_NAME);
                    Random rand = new Random();
                    int id = mas[rand.nextInt(mas.length)];
                    Long val = rand.nextLong() % 4000000;
                    System.out.println("Adding id: " + id + " value: " + val);
                    service.addAmount(id, val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class GetThread implements Runnable {
        public void run() {
            while(true) {
                try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 6969);
                    AccountService service = (AccountService) registry.lookup(BINDING_NAME);
                    Random rand = new Random();
                    int id = mas[rand.nextInt(mas.length)];
                    System.out.println("Get value with id: " + id + ".  Response: " + service.getAmount(id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
