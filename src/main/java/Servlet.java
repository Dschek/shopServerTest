import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(resp.toString());
        super.doGet(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int size = req.getContentLength();
        //req.getCharacterEncoding()
        if(size != 0){
            char[] c = new char[size];
            req.getReader().read(c);
            if(api.Good.addGood(String.valueOf(c))){
                resp.setStatus(200);
                resp.setHeader("1", "124124");
                System.out.println("true");
            }
        }
    }
}
