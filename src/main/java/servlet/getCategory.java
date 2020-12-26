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
import java.util.LinkedList;
import java.util.List;

public class getCategory extends HttpServlet {
    private final static Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String parentIdStr = req.getParameter("parentId");
        int id = 0, parentId = 0;
        if (idStr != null && idStr.matches("\\d+"))
            id = Integer.valueOf(idStr);
        if (parentIdStr != null && parentIdStr.matches("\\d+"))
            parentId = Integer.valueOf(parentIdStr);
//        String result = Category.getCategorysJson(id, name, parentId);
//        System.out.println(result);
//        if (result != null) {
//            resp.setContentType("application/json");
//            try (PrintWriter pw = resp.getWriter()) {
//                pw.write(result);
//            } catch (IOException e) {
//                log.trace("Error writer", e);
//            }
//        }
        List<Categorys> categorys = Category.getCategorys(0, null, 0);
        LinkedList<String> resultList = new LinkedList<>();
        if (categorys != null) {
            String link = "<p><a class=\"%s\" href=\"http://localhost:8081/getGoods?category=%s\">%s</a></p>";
            for (Categorys category : categorys) {
                int parentIdResulr = category.getParentId();
                if (parentIdResulr == 0)
                    resultList.addLast(String.format(link, "parentCategory", category.getId(), category.getName()));
                for (int i = 0, max = categorys.size(); i < max; i++)
                    if (categorys.get(i).getId() == parentIdResulr) {
                        resultList.add(String.format(link, "category", category.getId(), category.getName()));
                        break;
                    }
            }
        }
        String result = new String();
        if (!resultList.isEmpty())
            for (String category : resultList)
                result += category;
        System.out.println(result);
        req.setAttribute("meny", result);
        resp.setStatus(200);
        getServletContext().getRequestDispatcher("/good.jsp").forward(req, resp);
    }
}
