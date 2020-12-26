package tables;

import javax.persistence.*;

@Entity
@Table(name= "role")
public class Roles extends Model{

    @Column(name="name")
    private String name;

    public Roles() {
    }

    public Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
