package main;

import Servlets.AddServlet;
import Servlets.DeleteServlet;
import Servlets.SearchServlet;
import Servlets.ShowServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import vocabulary.Vocabulary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class Main {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static void main(String[] args) throws Exception {
        Vocabulary vocabulary = new Vocabulary();

        Connection connection = DriverManager.getConnection(DB_URL, "root", "root");//соединениесБД
        Statement statement = connection.createStatement();
//statement.execute("delete from vocabulary");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new AddServlet(vocabulary,connection)), "/add");
        context.addServlet(new ServletHolder(new DeleteServlet(vocabulary,connection)), "/delete");
        context.addServlet(new ServletHolder(new SearchServlet(vocabulary,connection)), "/search");
        context.addServlet(new ServletHolder(new ShowServlet(vocabulary,connection)), "/show");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html/vocabulary.html");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlerList);

        server.start();
        server.join();

    }
}
