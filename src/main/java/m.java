//import api.tables.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.query.NativeQuery;
//import tables.*;
//
//import java.util.List;
//
//public class m {
//    public static void main(String[] args) {
//        List<User> user= null;
//        try(SessionFactory sf = HibernateUtil.getSessionFactory();
//            Session session = sf.openSession();) {
//            try {
//                Transaction tran = session.beginTransaction();
//                //Select;
//                NativeQuery result =   session.createSQLQuery("select * from users");
//                result.addEntity(User.class);
//                user = result.list();
//                //
//                tran.commit();
//                result = session.createSQLQuery("select * from role");
//                System.out.println((String) result.getQueryString());
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                e.printStackTrace();
//            }
//            try {
//                Transaction tran = session.beginTransaction();
//                //Select;
//                NativeQuery result =   session.createSQLQuery("select * from users");
//                result.addEntity(User.class);
//                user = result.list();
//                //
//                tran.commit();
//                result = session.createSQLQuery("select * from role");
//                System.out.println((String) result.getQueryString());
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                e.printStackTrace();
//            }
//        }
//        for(User u:user) {
//            System.out.println(u.toString());
//        }
//    }
//}
