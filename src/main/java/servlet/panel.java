package servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class panel extends HttpServlet {
    private final static Logger log = LogManager.getLogger();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("panel.jsp").forward(req, resp);
        } catch (IOException e) {
            log.trace("Error writer", e);
        } catch (ServletException e) {
            log.trace("Error writer", e);
        }
    }
}
