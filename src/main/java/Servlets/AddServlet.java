package Servlets;

import vocabulary.Vocabulary;
import vocabulary.Word;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AddServlet extends HttpServlet {
    private final Vocabulary vocabulary;
    private final Connection connection;

    public AddServlet(Vocabulary vocabulary,Connection connection) {
        this.vocabulary = vocabulary;
        this.connection=connection;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        vocabulary.addWord(new Word(request.getParameter("russia"), request.getParameter("english")));
        response.getWriter().println("The word: " + request.getParameter("russia") + " "
                + request.getParameter("english") + " add in vocabulary");
        try {
        String russia=request.getParameter("russia");
        String english=request.getParameter("english");
        Statement statement=connection.prepareStatement("insert into vocabulary (russia, english) values ((?),(?))");
        ((PreparedStatement) statement).setString(1,russia);
        ((PreparedStatement) statement).setString(2,english);
        ((PreparedStatement) statement).executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
