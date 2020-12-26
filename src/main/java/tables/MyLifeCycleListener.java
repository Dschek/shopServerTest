package tables;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyLifeCycleListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        HibernateUtil.getSessionFactory();
    }

    public void contextDestroyed(ServletContextEvent event) {
        HibernateUtil.close();
    }
}
