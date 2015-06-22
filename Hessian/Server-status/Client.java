import com.caucho.hessian.client.HessianProxyFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ִלטענטי on 21.06.2015.
 */
public class Client {

    static StatisticService statisticService;
    static HessianProxyFactory factory;



    public static void main(String[] args) throws IOException{
        factory = new HessianProxyFactory();
        try {
            statisticService = (StatisticService) factory.create(StatisticService.class,"http://localhost:6969/stats");
        }
        catch (java.net.MalformedURLException e) {
            System.out.println("Bad URL");
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String token;
        System.out.println("Enter a command");
        while ((token = bufferedReader.readLine()) != null) {
            if (token.equals("all")) {
                System.out.println("Count call all methods: " + statisticService.allStat());
            } else if (token.equals("add")) {
                System.out.println("Calls addAmount per second: " + statisticService.addStat());
            } else if (token.equals("get")) {
                System.out.println("Calls getAmount per second: " + statisticService.getStat());
            } else if (token.equals("clear")) {
                System.out.println("Clearing stats ....");
                statisticService.clear();
                System.out.println("All stats clear");
            }
            System.out.println("Enter a command");
        }
    }
}
