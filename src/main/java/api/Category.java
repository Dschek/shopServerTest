package api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.Categorys;
import tables.HibernateUtil;

import java.util.List;

public class Category {

    private final static Logger log = LogManager.getLogger();

    public static Boolean addCategory(Categorys category) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tran = session.beginTransaction();
            session.save(category);
            tran.commit();
        } catch (Exception e) {
            log.trace("Error with the database.", e);
            return false;
        }
        return true;
    }

    public static boolean addCategory(String name, Integer parentId) {
        tables.Categorys category;
        try {
            category = new tables.Categorys(name, parentId);
        } catch (Exception e) {
            log.trace("Error in incoming parameters.", e);
            return false;
        }
        return addCategory(category);
    }

    public static boolean addCategory(String json) {
        if (json == null || json.isEmpty()) {
            log.trace("Category.addCategory. Error in incoming parameters. Json can not be null");
            return false;
        }
        Categorys category = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            category = mapper.readValue(json, Categorys.class);
        } catch (Exception e) {
            log.trace("Category.addCategory. Error in incoming parameters.", e);
            return false;
        }
        return addCategory(category);

    }

    public static List<Categorys> getCategorys(int id, String name, int parentId) {
        List<Categorys> category = null;
        try (Session session = HibernateUtil.getSession()) {
            category = session.createSQLQuery("SELECT category.* FROM category " +
                    "WHERE ( :id = 0 OR category.id = :id ) " +
                    "AND ( :name IS NULL OR category.name = :name ) " +
                    "AND ( :parentId = 0 OR category.parentId = :parentId ); ")
                    .setParameter("id", id)
                    .setParameter("name", name)
                    .setParameter("parentId", parentId)
                    .addEntity(Categorys.class)
                    .list();
            System.out.println(category.isEmpty() ? "Null" : "NotNull");
        } catch (Exception e) {
            System.out.println(e);
            log.trace("Category.getCategorys. Error with find category by id = (), name = (), parentId = () ", id, name, parentId, e);
        }
        if (category == null || category.size() == 0) {
            log.trace("Can not be find categorys by id = " + id);
            return null;
        }
        return category;
    }

    public static List<Categorys> getAllChildCategorys(int parentId) {
        List<Categorys> categorys = getCategorys(0, null, parentId);
        if (categorys == null) {
            log.trace("Can not be find categorys by parentId = " + parentId);
            return null;
        }
        for (int i = 0; i < categorys.size(); i++) {
            System.out.println("id = " + categorys.get(i).getId());
            List<Categorys> childCategorys = getCategorys(0, null, categorys.get(i).getId());
            if (childCategorys != null) {
                for (Categorys c : categorys)
                    System.out.print(c.getId());
                categorys.addAll(childCategorys);
            }
        }
        return categorys;
    }

    public static Integer[] getAllChildCategorysId(int parentId) {
        List<Categorys> categorys = getAllChildCategorys(parentId);
        if (categorys == null) {
            log.trace("Can not be find categorys by parentId = " + parentId);
            return new Integer[]{parentId};
        }
        Integer[] result = new Integer[categorys.size() + 1];
        result[0] = parentId;
        int i = 1;
        for (Categorys category : categorys)
            result[i++] = category.getId();
        return result;
    }

    public static Categorys getCategory(int id, String name, int parentId) {
        List<Categorys> categorys = getCategorys(id, name, parentId);
        if (categorys == null) {
            return null;
        }
        return categorys.get(0);
    }

    public static String getCategorysJson(int id, String name, int parentId) {
        String result = null;
        List<Categorys> categorys = getCategorys(id, name, parentId);
        if (categorys == null) {
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            result = mapper.writeValueAsString(categorys);
        } catch (Exception e) {
            log.trace("Role.getRoleJson. Error with get role param", e);
        }
        return result;
    }

    public static String getCategoryJson(int id, String name, int parentId) {
        String result = null;
        Categorys category = getCategory(id, name, parentId);
        if (category == null) {
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            result = mapper.writeValueAsString(category);
        } catch (Exception e) {
            log.trace("Category.getCategoryJson. Error with get role param", e);
        }
        return result;
    }
}