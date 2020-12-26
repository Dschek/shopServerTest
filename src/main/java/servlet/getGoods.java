package servlet;

import api.Good;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class getGoods extends HttpServlet {
    private final static Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println(1111);
        String name = req.getParameter("name");
        String param = req.getParameter("merchId");
        Integer merchId = null;
        if (param != null && param.matches("\\d+"))
            merchId = Integer.valueOf(param);
        param = req.getParameter("onlyHave");
        boolean onlyHave = false;
        if (param != null && !param.isEmpty() && param.toLowerCase().equals("true"))
            onlyHave = true;
        param = req.getParameter("categoryId");
        Integer categoryId = null;
        if (param != null && param.matches("\\d+"))
                categoryId = Integer.valueOf(param);

        param = req.getParameter("minPrice");
        BigDecimal minPrice = null;
        if (param != null && param.matches("\\d+([.]\\d+|[.]|)"))
            minPrice = new BigDecimal(param);
        param = req.getParameter("maxPrice");
        BigDecimal maxPrice = null;
        if (param != null && param.matches("\\d+([.]\\d+|[.]|)"))
            maxPrice = new BigDecimal(param);
        String search = req.getParameter("search");
        param = req.getParameter("start");
        Integer start = null;
        if (param != null && param.matches("\\d+"))
            start = Integer.valueOf(param);
        param = req.getParameter("end");
        Integer end = null;
        if (param != null && param.matches("\\d+"))
            end = Integer.valueOf(param);
        String result = Good.getGoods(name, merchId, onlyHave, categoryId, minPrice, maxPrice, search, start, end);
        System.out.println(result);
        resp.setStatus(200);
        try (PrintWriter pw = resp.getWriter()) {
            if (result != null)
                pw.write(result);
            else
                pw.write("Can not be find goods");
        } catch (IOException e) {
            log.trace("Error work writer", e);
        }
    }
}
