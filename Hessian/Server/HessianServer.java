import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;


public class HessianServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(6969);
        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        server.setHandler(context);
        context.addServlet(BasicService.class, "/Basic");
        server.start();
        server.join();
    }

}
