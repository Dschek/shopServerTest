package tables;

import javax.persistence.*;

@Entity
@Table(name ="orderGoods")
public class OrderGoods {
    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "goodId",
                    column = @Column(name="goodId")),
            @AttributeOverride(name = "orderId",
                    column = @Column(name="orderId"))
    })
    private int goodId;
    private int orderId;
    private int price;
    private int count;

    public OrderGoods() {
    }

    public OrderGoods(int orderId, int price, int count) {
        this.orderId = orderId;
        this.price = price;
        this.count = count;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
