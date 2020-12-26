package tables;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sf = null;

    static {
        Configuration cfg = new Configuration().configure();
        cfg.addAnnotatedClass(Carts.class);
        cfg.addAnnotatedClass(Categorys.class);
        cfg.addAnnotatedClass(Goods.class);
        cfg.addAnnotatedClass(Orders.class);
        cfg.addAnnotatedClass(Roles.class);
        cfg.addAnnotatedClass(Titles.class);
        cfg.addAnnotatedClass(Users.class);
        cfg.addAnnotatedClass(OrderGoods.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        StandardServiceRegistry serviceRegistry = builder.build();
        try {
            sf = cfg.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }

    public static Session getSession() {
        return sf.openSession();
    }

    public static void close() {
        sf.close();
    }


}
