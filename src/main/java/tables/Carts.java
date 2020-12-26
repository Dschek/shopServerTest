package tables;

import javax.persistence.*;

@Entity
@Embeddable
@Table(name = "cart")
public class Carts {

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "userId",
                    column = @Column(name="userId")),
            @AttributeOverride(name = "goodId",
                    column = @Column(name="goodId"))
    })

  //  @ManyToOne
    private int userId;

   // @ManyToOne
    private int goodId;

    @Column(name="count")
    private int count;

    public Carts() {
    }

    public Carts(int userId, int goodId, int count) {
        this.userId = userId;
        this.goodId = goodId;
        this.count = count;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId : " + userId +
                ", goodId :" + goodId +
                ", count :" + count +
                '}';
    }
}
