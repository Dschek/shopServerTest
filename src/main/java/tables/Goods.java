package tables;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "goods")
public class Goods extends Model{
    @Column(name="name")
    private String name;
    @Column(name="merchId")
    private int merchId;
    @Column(name= "count")
    private int cunt;
    @Column(name="soult")
    private int soult;
    @Column(name="categoryId")
    private int categoryId;
    @Column(name="price")
    private BigDecimal price;
    @Column(name="picture")
    private String picture;
    private String title;

    public Goods() {
    }

    public Goods(String name, int merchId, int cunt, int soult, int categoryId, BigDecimal price, String picture, String title) {
        this.name = name;
        this.merchId = merchId;
        this.cunt = cunt;
        this.soult = soult;
        this.categoryId = categoryId;
        this.price = price;
        this.picture = picture;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getMerchId() {
        return merchId;
    }

    public void setMerchId(int merchId) {
        this.merchId = merchId;
    }

    public int getCunt() {
        return cunt;
    }

    public void setCunt(int cunt) {
        this.cunt = cunt;
    }

    public int getSoult() {
        return soult;
    }

    public void setSoult(int soult) {
        this.soult = soult;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", merchId=" + merchId +
                ", cunt=" + cunt +
                ", soult=" + soult +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
