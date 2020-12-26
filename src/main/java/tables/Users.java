package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users extends Model{
    @Column(name = "statusId")
    private int statusId;
    @Column(name = "name")
    private String name;
    @Column(name = "surName")
    private String surName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "pageUrl")
    private String pageUrl;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    public Users(){
    }

    public Users(int statusId, String name, String surName, String lastName, String pageUrl, String email, String password) {
        if(email == null || email.isEmpty())
            throw new IllegalArgumentException("email must be not null");
        this.email = email;
        this.pageUrl = ( pageUrl == null || pageUrl.isEmpty() ) ? email : pageUrl;
        this.statusId = statusId;
        this.name = name;
        this.surName = surName;
        this.lastName = lastName;
        this.password = password;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
