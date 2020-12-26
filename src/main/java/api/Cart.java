package api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.Carts;
import tables.Goods;
import tables.HibernateUtil;

import java.util.List;

public class Cart {
    private final static Logger log = LogManager.getLogger();

    public static boolean addCart(Carts cart) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tran = session.getTransaction();
            session.save(cart);
            tran.commit();
        } catch (Exception e) {
            log.trace("Cart.addCart. Error with the database.", e);
            return false;
        }
        return true;
    }

    public static boolean addCart(String json) {
        if (json == null || json.isEmpty()) {
            log.trace("Cart.addCart. Error in incoming parameters. Json can not be null");
            return false;
        }
        Carts cart = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            cart = mapper.readValue(json, Carts.class);
        } catch (Exception e) {
            log.trace("Cart.addCart. Error in incoming parameters.", e);
            return false;
        }
        return addCart(cart);
    }

    public static boolean addCart(int userId, int goodId, int count) {
        return addCart(new Carts(userId, goodId, count));
    }

    public static List<Object[]> getCartsByUserId(int userId) {
        List<Object[]> cartsList = null;
        try (Session session = HibernateUtil.getSession()) {
            cartsList = session.createSQLQuery("SELECT goods.*, cart.* FROM goods, cart WHERE goods.id = cart.goodId AND cart.userId = :userId")
                    .setParameter("userId", userId)
                    .addEntity("goods", Goods.class)
                    .addEntity("title", Carts.class)
                    .list();
        } catch (Exception e) {
            log.trace("Cart.getCartsByUserId. Error with the database.", e);
            return null;
        }
        if (cartsList == null || cartsList.isEmpty()) {
            log.trace("Can not be find carts by userId");
            return null;
        }
        return cartsList;
    }

    public static String getCartsJson(int userId) {
        List<Object[]> cartsList = getCartsByUserId(userId);
        String result = null;
        if (cartsList == null || cartsList.size() == 0)
            return null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            result = mapper.writeValueAsString(cartsList);
        } catch (NullPointerException e) {
            log.trace("Cart.getCartJson. Can not be find good by id = " + userId, e);
        } catch (Exception e) {
            log.trace("Cart.getCartJson. Error with get good param", e);
        }
        return result;
    }

    public static List<Carts> getCartsByGoodId(int goodId) {
        List<Carts> cartsList = null;
        try (Session session = HibernateUtil.getSession()) {
            cartsList = session.createSQLQuery("SELECT cart.* FROM cart WHERE cart.goodId = :goodId")
                    .setParameter("goodId", goodId)
                    .addEntity(Carts.class)
                    .list();
        } catch (Exception e) {
            log.trace("Cart.getCartsByGoodId. Error with the database.", e);
            return null;
        }
        if (cartsList == null || cartsList.isEmpty()) {
            log.trace("Can not be find carts by goodId");
            return null;
        }
        return cartsList;
    }

    public static String getCartsByGoodIdJson(int goodId) {
        List<Carts> cartsList = getCartsByGoodId(goodId);
        if (cartsList == null) {
            log.trace("Carts can not be find by goodId");
            return null;
        }
        String result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            result = mapper.writeValueAsString(cartsList);
        } catch (Exception e) {
            log.trace("Cart.getCartsByGoodIdJson. Error with get cart param", e);
        }
        return result;
    }

//    public static String getCartsByUserIdJson(int userId) {
//        List<Carts> cartsList = getCartsByUserId(userId);
//        if (cartsList == null) {
//            log.trace("Cats can not be find by userId");
//            return null;
//        }
//        String result = null;
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            result = mapper.writeValueAsString(cartsList);
//        } catch (Exception e) {
//            log.trace("Error. error write carts in json.");
//        }
//        return result;
//    }

}

