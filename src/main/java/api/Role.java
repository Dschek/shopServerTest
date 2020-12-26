package api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.HibernateUtil;
import tables.Roles;

import java.util.List;

public class Role {
    private final static Logger log = LogManager.getLogger();

    public static Roles getRole(int id){
        List<Roles> roles = null;
        try(Session session = HibernateUtil.getSession()) {
            roles = session.createSQLQuery("SELECT role.* FROM role WHERE role.id = :id")
                    .setParameter("id", id)
                    .addEntity(Roles.class)
                    .list();
        } catch(Exception e){
            log.trace("Role.getRole. Error with find role by id = " + id, e);
        }
        if(roles == null || roles.size() == 0){
            log.trace("Can not be find role by id = "+ id);
            return null;
        }
        return roles.get(0);
    }

    public static String getRoleJson( int id){
        String result = null;
        Roles role = getRole(id);
        if(role == null){
            log.trace("Role.getRoleJson. Error with find role by id" + id);
            return null;
        }
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            result = mapper.writeValueAsString(role);
        }
        catch(Exception e){
            log.trace("Role.getRoleJson. Error with get role param", e);
        }
        return result;
    }

    public static boolean addRole(Roles role){
        try (Session session  = HibernateUtil.getSession()) {
            Transaction tran = session.beginTransaction();
            session.save(role);
            tran.commit();
        }
        catch (Exception e){
            log.trace("Role.addRole. Error with the database.", e);
            return false;
        }
        return true;
    }

    public static boolean addRole(String name){
        if(name == null || name.isEmpty()){
            log.trace("Role.addRole. Error in incoming parameters. Name can not be null");
            return false;
        }
        Roles role = new Roles(name);
        return addRole(role);
    }

    public static boolean addRoleJson(String json){
        if(json == null || json.isEmpty()){
            log.trace("Role.addRoleJson. Error in incoming parameters. Name can not be null");
            return false;
        }
        Roles role = null;
        try{
            ObjectMapper mapper = new ObjectMapper();
            role = mapper.readValue(json, Roles.class);
        } catch (Exception e) {
            log.trace("Role.addRoleJson. Error in incoming parameters.", e);
            return false;
        }
        return addRole(role);
    }

    public static boolean changeRole(int id, String name){
        if(name == null || name.isEmpty()){
            log.trace("Role.changeRole. Parameter name can not be null.");
            return false;
        }
        Roles role = getRole(id);
        if(role== null){
            log.trace("Role.changeRole. Error with find role by id = "+ id);
            return false;
        }
        role.setName(name);
        return addRole(role);
    }
}
