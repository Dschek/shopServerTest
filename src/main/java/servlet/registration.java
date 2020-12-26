package servlet;

import api.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tables.Users;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

public class registration extends HttpServlet {
    private final static Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String result = new String();
        resp.setCharacterEncoding("UTF-8");
        if (session == null || session.getAttribute("userId") == null)
            result = api.pages.getRegistration();
        else {
            Users user = User.getUser((Integer) session.getAttribute("userId"));
            result = api.pages.getProfile(user.getName(), user.getLastName(), user.getSurName(), user.getEmail());
        }
        try (PrintWriter pw = resp.getWriter()) {
            System.out.println(result);
            pw.write(result);
        } catch (IOException e) {
            System.out.println(e);
            log.trace("Error writer", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String json = "";
        try (BufferedReader reader = req.getReader();
             Stream<String> stream = reader.lines()) {
            Object[] params = stream.toArray();
            for (Object param : params)
                json += param;
        } catch (IOException e) {
            System.out.println(e);
            log.trace("Error reader body", e);
        }
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("userId") == null) {
            User.addUser(json);
        } else
            User.changeUser((Integer) session.getAttribute("userId"), json);
    }
}
