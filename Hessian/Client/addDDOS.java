import java.util.Random;

/**
 * Created by ִלטענטי on 21.06.2015.
 */
public class AddDDOS extends Thread {

    public void run() {
        Random random = new Random();
        while (true){
            Integer id = random.nextInt(Client.list.size());
            //from [-2;2]
            Client.accountService.addAmount(Client.list.get(id), random.nextLong() % 5 - 2);
        }
    }
}
