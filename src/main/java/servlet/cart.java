package servlet;

import api.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class cart extends HttpServlet {
    private final static Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String result = new String();
        if (req.getSession() == null || req.getSession().getAttribute("userId") == null)
            result = "{'result':'false'}";
        else {
            Integer userId = (Integer) req.getSession().getAttribute("userId");
            result = Cart.getCartsJson(Integer.valueOf(userId));
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