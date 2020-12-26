package servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

public class addGood extends HttpServlet {
    private final static Logger log = LogManager.getLogger();
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
            resp.setStatus(200);
            if(api.Good.addGood(json)){
                try(PrintWriter pw = resp.getWriter()){
                    pw.write("Approve");
                }catch(IOException e){
                    log.trace("Error with work writer");
                }
            }
        }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String result = new String();
        if (session == null || session.getAttribute("userId") == null)
            result = api.pages.getLogin();
        else
            result = api.pages.getAddContent();
        try (PrintWriter pw = resp.getWriter()) {
            System.out.println(result);
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            pw.write(result);
        } catch (IOException e) {
            log.trace("Error writer", e);
        }
    }
}
