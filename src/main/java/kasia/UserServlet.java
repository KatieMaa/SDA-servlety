package kasia;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RENT on 2017-03-01.
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = Arrays.asList(
                new User("Symon", "Mazek"),
                new User("Jan", "Nowak"),
                new User("Anna", "Mazurek"));

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        // writeUser(userList, writer);
        //writeUsersWithParam(req, userList, writer);

        String requestURI = req.getRequestURI();
        String counterAsString = requestURI.substring((requestURI.lastIndexOf('/') + 1));
        Integer counter = new Integer(counterAsString);
        User user = userList.get(counter);
        writer.write("<h1>" + user.getFirstName() + " " + user.getLastName() + "</h1>");


    }

    private void writeUsersWithParam(HttpServletRequest req, List<User> userList, PrintWriter writer) {
        Integer counter = new Integer(req.getParameter("counter"));
        writer.write("<ul>");
        userList.stream()
                .limit(counter)
                .forEach(e -> writer.write("<li>" + e.getFirstName() + " " + e.getLastName() + "</li>"));
        writer.write("</ul>");
    }

    private void writeUser(List<User> userList, PrintWriter writer) {
        writer.write("<ul>");
        userList.forEach(e -> writer.write("<li>" + e.getFirstName() + " " + e.getLastName() + "</li>"));
        writer.write("</ul>");
    }
}
