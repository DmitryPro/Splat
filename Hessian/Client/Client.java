import com.caucho.hessian.client.HessianProxyFactory;

/**
 * Created by ִלטענטי on 19.06.2015.
 */
public class Client {

    public static void main(String[] args) throws Exception {
        HessianProxyFactory factory =new HessianProxyFactory();
        AccountService accountService = (AccountService) factory.create(AccountService.class,"http://localhost:6969/service");
        System.out.println(accountService.getAmount(1));
    }

}
