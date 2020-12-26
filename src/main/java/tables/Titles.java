package tables;

import javax.persistence.*;

@Entity
@Table(name="title")
public class Titles {
    @Id
    @Column(name="goodId")
    private int goodId;
    @Column(name="title")
    private String title;

    public Titles() {
    }

    public Titles(int goodId, String title) {
        this.goodId = goodId;
        this.title = title;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Title{" +
                "goodId=" + goodId +
                ", title='" + title + '\'' +
                '}';
    }
}
