import java.util.Random;

/**
 * Created by ִלטענטי on 21.06.2015.
 */
public class GetDDOS extends Thread {

    public void run() {
        Random random = new Random();
        while (true) {
            Integer id = random.nextInt(Client.list.size());
            Client.accountService.getAmount(Client.list.get(id));
        }
    }
}
