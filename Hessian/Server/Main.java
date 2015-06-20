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

        ConnectorStatistics statistics = new ConnectorStatistics();
        NetworkTrafficServerConnector connector = new NetworkTrafficServerConnector(server);
        connector.addBean(statistics);
        connector.setPort(6969);
        context.addServlet(DataManager.class, "/service");
        server.setConnectors(new Connector[]{connector});

        server.start();
        server.join();
    }
}
