package pl.treekt.mychunk.Entity.Web;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @Column(name = "short_description")
    private String shortDescription;
    private String description;
    private float transferPrice;
    private String image;

    @ManyToOne
    @JoinColumn(name = "sms_id")
    private SMS sms;

    @OneToMany(mappedBy = "position")
    private List<Command> commands;

    @OneToMany(mappedBy = "position")
    private Set<SMSPayment> smsHistories;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SMS getSms() {
        return sms;
    }

    public void setSms(SMS sms) {
        this.sms = sms;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public float getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(float transferPrice) {
        this.transferPrice = transferPrice;
    }
}
