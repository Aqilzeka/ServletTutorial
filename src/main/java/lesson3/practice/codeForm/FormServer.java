package lesson3.practice.codeForm;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class FormServer {
    public static void main(String[] args) throws Exception {
        Server server  = new Server(8081);

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new GoodCodeServlet()),"/goodCode");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
