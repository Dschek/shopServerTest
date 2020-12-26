package api;

public class pagesUser {
    private Integer login = null;
    private String password = null;

    public pagesUser() {
    }

    public Integer getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }
}
