package servlet;

import api.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tables.Categorys;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class home extends HttpServlet {
    private final static Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        List<Categorys> categorys = Category.getCategorys(0, null, 0);
        int size = categorys.size();
        for (int i = 1, parentId = categorys.get(i).getParentId(); i < size; parentId = categorys.get(i++).getParentId())
            for (int j = 0; j < i; j++)
                if (parentId < categorys.get(i).getParentId()) {
                    categorys.add(j, categorys.get(i));
                    categorys.remove(i + 1);
                    break;
                }
        String link = "<li><a href=\"%s\">%s</a><ul class=\"dropdown\">";
        Categorys cat = categorys.get(0);
        String result = String.format(link, cat.getId(), cat.getName());
        link += "</li>";
        System.out.println(result);
        for (int i = 1; i < size; i++) {
            cat = categorys.get(i);
            if (cat.getParentId() == categorys.get(i - 1).getId())
                result += "<ul class=\"dropdown\">";
            else if (cat.getParentId() != categorys.get(i - 1).getParentId()
                    && categorys.get(i - 1).getParentId() == categorys.get(i - 2).getParentId())
                result += "</ul>";
            result += String.format(link, cat.getId(), cat.getName());
            System.out.println(result);
        }
        System.out.println(result);
        req.setAttribute("meny", result);
        resp.setStatus(200);
        try {
            getServletContext().getRequestDispatcher("/good.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println( e);
        }
    }
}
