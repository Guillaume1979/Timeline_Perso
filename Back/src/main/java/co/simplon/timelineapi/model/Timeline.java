package co.simplon.timelineapi.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Timeline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date creationDate;

    private Date updateDate;

    private String category;

    @OneToMany(mappedBy = "timeline")
    @OrderBy("id asc")
    private Set<Card> cardList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Set<Card> getCardList() {
        return cardList;
    }

    public void setCardList(Set<Card> cardList) {
        this.cardList = cardList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
