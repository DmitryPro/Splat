import com.caucho.hessian.client.HessianProxyFactory;

public class HessianClient {

    public static void main(String[] args) throws Exception {
            HessianProxyFactory factory = new HessianProxyFactory();
            Basic basic = (Basic) factory.create(Basic.class,
                    "http://localhost:6969/Basic");
            System.out.println("Hello: " + basic.hello());

    }

}

