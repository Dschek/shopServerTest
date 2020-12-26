package tables;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="orders")
public class Orders extends  Model{
    @Column(name="status")
    private int status;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="custId")
    private int custId;

    public Orders(){}

    public Orders(int status, BigDecimal price, int custId) {
        this.status = status;
        this.price = price;
        this.custId = custId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    @Override
    public String toString() {
        return "Order{" +
                super.toString() +
                ", status=" + status +
                ", price=" + price +
                ", custId=" + custId +
                '}';
    }
}
