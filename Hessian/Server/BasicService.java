import com.caucho.hessian.server.HessianServlet;

public class BasicService extends HessianServlet implements Basic {

    public String hello() {
        return "Hello! It works!";
    }

}