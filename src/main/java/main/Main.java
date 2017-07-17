package main;


import Servlet.Chat;
import Servlet.Login;
import accounts.AccountService;
import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by alex on 17.7.17.
 */
public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        try {
            AccountService accountService = new AccountService();
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(new Login(dbService,accountService)), "/login");
            context.addServlet(new ServletHolder(new Chat(dbService,accountService)), "/chat");

            ResourceHandler resource_handler = new ResourceHandler();
            resource_handler.setResourceBase("public_html");

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resource_handler, context});

            Server server = new Server(8081);
            server.setHandler(handlers);



            dbService.sendMessege("ALex","Bob","Hello World");
            dbService.sendMessege("ALex","Billy","Hi, man");

            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
