package servlet;

import api.Good;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class getGood extends HttpServlet {
    private final static Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        if (id != null && id.matches("\\d+")) {
            String result = Good.getGoodJson(Integer.valueOf(id));
            if (result != null) {
                resp.setContentType("application/json");
                try (PrintWriter pw = resp.getWriter()) {
                    pw.write(result);
                } catch (IOException e) {
                    log.trace("Error writer", e);
                }
            }
        }
    }
}