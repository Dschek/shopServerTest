package servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class logOut extends HttpServlet {
    private final static Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String result = "";
        if (session != null && session.getAttribute("userId") != null)
            session.removeAttribute("userId");
        try (PrintWriter pw = resp.getWriter()) {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            pw.write(result);
        } catch (IOException e) {
            log.trace("Error writer", e);
        }
    }
}
