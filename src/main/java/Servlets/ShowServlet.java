package Servlets;

import vocabulary.Vocabulary;
import vocabulary.Word;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class ShowServlet extends HttpServlet {
    private final Vocabulary vocabulary;
    private final Connection connection;
    public ResultSet resultSet;
    public Statement statement;

    public ShowServlet(Vocabulary vocabulary, Connection connection) {
        this.vocabulary = vocabulary;
        this.connection = connection;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            statement = connection.prepareStatement("select russia, english from vocabulary ");
            resultSet = ((PreparedStatement) statement).executeQuery();
            String vocab = "";
            while (resultSet.next()) {
                String russia = resultSet.getString("russia");
                String english = resultSet.getString("english");
                vocab = russia + " " + english + "\n";
                response.getWriter().println("Vocabulary: " + "\n" + vocab);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
