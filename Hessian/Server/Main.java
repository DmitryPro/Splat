import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.server.Connector;

/**
 * Created by ִלטענטי on 10.06.2015.
 */


public class Main {

    public static void main(String[] args) throws Exception{
        Server server = new Server(new QueuedThreadPool(8));

        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        server.setHandler(context);
        NetworkTrafficServerConnector connector = new NetworkTrafficServerConnector(server);
        connector.setPort(6969);

        context.addServlet(ServerManager.class,"/stats").setInitOrder(0);
        context.addServlet(DataManager.class, "/service").setInitOrder(0);
        server.setConnectors(new Connector[]{connector});

        server.start();
        server.join();
    }
}
