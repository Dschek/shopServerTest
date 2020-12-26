package api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tables.HibernateUtil;
import tables.Users;

import java.util.List;

public class User {
    private final static Logger log = LogManager.getLogger();

    public static boolean addUser(Users user) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tran = session.beginTransaction();
            session.save(user);
            tran.commit();
        } catch (Exception e) {
            log.trace("Error with the database.", e);
            return false;
        }
        return true;
    }

    public static boolean addUser(int statusId, String name, String surName, String lastName, String pageUrl, String email, String password) {
        Users user = null;
        try {
            user = new Users(statusId, name, surName, lastName, pageUrl, email, password);
        } catch (Exception e) {
            log.trace("Error in incoming parameters.", e);
            return false;
        }
        return addUser(user);
    }

    public static boolean addUser(String json) {
        if (json == null || json.isEmpty()) {
            log.trace("Error with the add user. json can not be null or empty.");
            return false;
        }
        tables.Users user;
        try {
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(json, Users.class);
            System.out.println(user.toString());
        } catch (Exception e) {
            log.trace("Error in incoming parameters.", e);
            return false;
        }
        return addUser(user);
    }

    public static Users getUser(int id) {
        List<Users> userList = null;
        try (Session session = HibernateUtil.getSession()) {
            userList = session.createSQLQuery("SELECT users.* FROM users WHERE users.id = :id")
                    .setParameter("id", id)
                    .addEntity(Users.class)
                    .list();
        } catch (Exception e) {
            log.trace("Error work with database. ", e);
            return null;
        }
        if (userList == null || userList.size() == 0) {
            log.trace("Can not be find User by id = " + id);
            return null;
        }
        return userList.get(0);
    }

    public static Integer hasUser(Integer id, String password) {
        List<Users> userList = null;
        try (Session session = HibernateUtil.getSession()) {
            userList = session.createSQLQuery("SELECT users.* FROM users WHERE users.id = :id AND users.password = :password ;")
                    .setParameter("id", id)
                    .setParameter("password", password)
                    .addEntity(Users.class)
                    .list();
        } catch (Exception e) {
            log.trace("Error work with database. ", e);
            return null;
        }
        if (userList == null || userList.size() == 0) {
            log.trace("Can not be find User by id = " + id + (password == null ? "" : ("and passport = " + password)));
            return null;
        }
        return id;
    }

    public static Integer hasUser(String json) {
        if (json == null || json.isEmpty()) {
            log.trace("Error with the add user. json can not be null or empty.");
            return null;
        }
        pagesUser user;
        try {
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(json, pagesUser.class);
        } catch (Exception e) {
            log.trace("Error in incoming parameters.", e);
            return null;
        }
        return hasUser(user.getLogin(), user.getPassword());
    }

    public static Integer getUserId(String email) {
        List<Users> userList = null;
        try (Session session = HibernateUtil.getSession()) {
            userList = session.createSQLQuery("SELECT users.* FROM users WHERE users.email = :email;")
                    .setParameter("email", email)
                    .addEntity(Users.class)
                    .list();
        } catch (Exception e) {
            log.trace("Error work with database. ", e);
            return null;
        }
        if (userList == null || userList.size() == 0) {
            log.trace("Can not be find User by email = " + email);
            return null;
        }
        return userList.get(0).getId();
    }

    public static String getUserJson(int id) {
        String result = null;
        Users user = getUser(id);
        if (user == null) {
            log.trace("Can not be find User by id");
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            result = mapper.writeValueAsString(user);
        } catch (Exception e) {
            log.trace("User.getUserJson. Error with get user param", e);
        }
        return result;
    }

    public static boolean changeParam(int id, String nameParam, String valueParam) {
        Users user = getUser(id);
        if (user == null) {
            log.trace("Can not be find User by id");
            return false;
        }
        switch (nameParam) {
            case "name":
                user.setName(valueParam);
                break;
            case "surName":
                user.setSurName(valueParam);
                break;
            case "lastName":
                user.setLastName(valueParam);
                break;
            case "pageUrl":
                user.setPageUrl(valueParam);
                break;
            case "password":
                user.setPassword(valueParam);
                break;
            default:
                log.trace("Invalid parameter name " + nameParam + " for class User");
                return false;
        }
        return addUser(user);
    }

    public static boolean changeUser(Integer userId, String json) {
        if (json == null || json.isEmpty()) {
            log.trace("Error with the change user. json can not be null or empty.");
            return false;
        }
        tables.Users user = getUser(userId);
        if (user == null) {
            log.trace("Error with the change user. Can not be find user by userId = " + userId);
            return false;
        }
        tables.Users userParam=null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            userParam = mapper.readValue(json, Users.class);
            System.out.println(user.toString());
        } catch (Exception e) {
            log.trace("Error in incoming parameters.", e);
            return false;
        }
        user.setName(userParam.getName());
        user.setSurName(userParam.getSurName());
        user.setLastName(userParam.getLastName());
        user.setEmail(userParam.getEmail());
        if(userParam.getPassword()!= null)
            user.setPassword(userParam.getPassword());
        return addUser(user);
    }
}
