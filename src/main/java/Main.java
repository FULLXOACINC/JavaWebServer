import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by alex on 8.7.17.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        FrontEnd frontend =new FrontEnd();

        Server server =new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);
        context.addServlet(new ServletHolder(frontend),"/authform");

        server.start();
        server.join();

    }
}
