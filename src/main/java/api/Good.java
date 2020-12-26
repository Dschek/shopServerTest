package api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.Goods;
import tables.HibernateUtil;
import tables.Titles;

import java.math.BigDecimal;
import java.util.List;

public class Good {
    private final static Logger log = LogManager.getLogger();

    public static boolean addGood(Goods good) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tran = session.beginTransaction();
            session.save(good);
            Titles title = new Titles(good.getId(), good.getTitle());
            session.save(title);
            tran.commit();
        } catch (Exception e) {
            log.trace("Error with the database.", e);
            return false;
        }
        return true;
    }

    public static boolean addGood(String name, int merchId, int cunt, int soult, int categoryId, BigDecimal price, String picture, String title) {
        tables.Goods good;
        try {
            good = new tables.Goods(name, merchId, cunt, soult, categoryId, price, picture, title);
        } catch (Exception e) {
            log.trace("Error in incoming parameters.", e);
            return false;
        }
        return addGood(good);
    }

    public static boolean addGood(String json) {
        if (json == null || json.isEmpty()) {
            log.trace("Error with the add good. json can not be null or empty.");
            return false;
        }
        tables.Goods good;
        Titles tableTitle = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            good = mapper.readValue(json, Goods.class);
        } catch (Exception e) {
            log.trace("Error in incoming parameters.", e);
            return false;
        }
        return addGood(good);
    }

    public static Goods getGood(int id) {
        Goods good = null;
        List<Object[]> goodsTitlesList = null;
        try (Session session = HibernateUtil.getSession()) {
            goodsTitlesList = session.createSQLQuery("SELECT goods.*, title.* FROM goods, title WHERE goods.id = :id AND title.goodId = :id")
                    .setParameter("id", id)
                    .addEntity("goods", Goods.class)
                    .addEntity("title", Titles.class)
                    .list();
        } catch (Exception e) {
            log.trace("Error in search good by id.");
            return null;
        }
        if (goodsTitlesList == null || goodsTitlesList.size() == 0) {
            log.trace("Can not be find good by id = " + id);
            return null;
        }
        good = (Goods) goodsTitlesList.get(0)[0];
        good.setTitle(((Titles) goodsTitlesList.get(0)[1]).getTitle());
        return good;
    }

    public static String getGoodJson(int id) {
        Goods good = getGood(id);
        if (good == null) {
            log.trace("Can not be find good by id");
            return null;
        }
        String result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            result = mapper.writeValueAsString(good);
        } catch (NullPointerException e) {
            log.trace("Good.getGoodJson. Can not be find good by id = " + id, e);
        } catch (Exception e) {
            log.trace("Role.getGoodJson. Error with get good param", e);
        }
        return result;
    }

    public static String getGoods(String name, Integer merchId, boolean onlyHave, Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String search, Integer start, Integer end) {
        Integer[] categorysId = Category.getAllChildCategorysId(categoryId == null ? 0 : categoryId);
        System.out.println("sss");
        for (Integer integer : categorysId)
            System.out.println(integer);
        System.out.println("zzz");
        String result = null;
        List<Goods> goods = null;
        String sqlQuery = "SELECT goods.* FROM goods, title " +
                "WHERE " +
                "(:onlyHave = FALSE OR count != 0 ) " +
                "AND  goods.categoryId IN ( :categorysId ) " +
                "AND ( :merchId IS NULL OR goods.merchId  = :merchId) " +
                "AND ( :name IS NULL OR goods.name LIKE '%:name%') " +
                "AND ( :maxPrice IS NULL OR goods.price < :maxPrice) " +
                "AND ( :minPrice IS NULL OR goods.price > :minPrice) " +
                "AND title.goodId = goods.id " +
                "AND ( :search IS NULL OR title.title LIKE '%:search%' OR goods.name LIKE '%:search%' );";
        try (Session session = HibernateUtil.getSession()) {
            goods = session.createSQLQuery(sqlQuery)
                    .setParameter("onlyHave", onlyHave)
                    .setParameterList("categorysId", categorysId)
                    .setParameter("merchId", merchId)
                    .setParameter("name", name)
                    .setParameter("maxPrice", maxPrice)
                    .setParameter("minPrice", minPrice)
                    .setParameter("search", search)
                    .addEntity(Goods.class)
                    .list();
        } catch (Exception e) {
            log.trace("Error work with database ", e);
            return null;
        }
        if (goods == null) {
            log.trace("Can not be find goods");
            return null;
        }
        if (end != null || start != null) {
            if (end == null || end > goods.size())
                end = goods.size();
            if (start == null)
                start = 0;
            else if (start > end)
                return null;
            goods = goods.subList(start, end);
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            result = mapper.writeValueAsString(goods);
        } catch (JsonProcessingException e) {
            log.trace("Error with get goods, while serializable in JSON ", e);
        }
        return result;
    }

    public static boolean changeParam(int id, String nameParam, String valueParam) {
        Goods good = getGood(id);
        if (good == null) {
            log.trace("Can not be find good by id");
            return false;
        }
        switch (nameParam) {
            case "name":
                good.setName(valueParam);
                break;
            case "picture":
                good.setPicture(valueParam);
                break;
            case "title":
                good.setTitle(valueParam);
                break;
            default:
                log.trace("Invalid parameter name " + nameParam + " for class User");
                return false;
        }
        return addGood(good);
    }

//    public static Goods getCart(int userId) {
//        Goods good = null;
//        List<Object[]> goodsTitlesList = null;
//        try (Session session = HibernateUtil.getSession()) {
//            goodsTitlesList = session.createSQLQuery("SELECT goods.*, cart.* FROM goods, cart WHERE goods.id = cart.goodId AND cart.userId = :userId")
//                    .setParameter("userId", userId)
//                    .addEntity("goods", Goods.class)
//                    .addEntity("title", Carts.class)
//                    .list();
//        } catch (Exception e) {
//            log.trace("Error in search good by id.");
//            return null;
//        }
//        if (goodsTitlesList == null || goodsTitlesList.size() == 0) {
//            log.trace("Can not be find good by id = " + id);
//            return null;
//        }
//        good = (Goods) goodsTitlesList.get(0)[0];
//        good.setTitle(((Titles) goodsTitlesList.get(0)[1]).getTitle());
//        return good;
//    }


}