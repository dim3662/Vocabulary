package Servlets;

import vocabulary.Vocabulary;
import vocabulary.Word;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class SearchServlet extends HttpServlet {
    private final Vocabulary vocabulary;
    private final Connection connection;
    public ResultSet resultSet;
    public Statement statement;

    public SearchServlet(Vocabulary vocabulary, Connection connection) {
        this.vocabulary = vocabulary;
        this.connection = connection;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String russia = request.getParameter("russia");
        String english = request.getParameter("english");
        Word searchWord = new Word();


        if (russia.equals("")) {
            searchWord = vocabulary.searchFromEnglish(english);
            try {
                statement = connection.prepareStatement("select russia, english from vocabulary where english=(?)");
                ((PreparedStatement) statement).setString(1, english);
                resultSet = ((PreparedStatement) statement).executeQuery();
                resultSet.next();
                response.getWriter().println(resultSet.getString("russia") + " " + resultSet.getString("english"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (english.equals("")) {
            searchWord = vocabulary.searchFromRussia(russia);
            try {
                statement = connection.prepareStatement("select russia, english from vocabulary where russia=(?)");
                ((PreparedStatement) statement).setString(1, russia);
                resultSet = ((PreparedStatement) statement).executeQuery();
                resultSet.next();
                response.getWriter().println(resultSet.getString("russia") + " " + resultSet.getString("english"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
