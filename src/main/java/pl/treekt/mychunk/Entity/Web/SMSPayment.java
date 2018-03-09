package pl.treekt.mychunk.Entity.Web;

import org.hibernate.annotations.Type;
import pl.treekt.mychunk.Entity.Game.Player;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "sms_payment")
public class SMSPayment implements Comparable<SMSPayment>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String code;

    @Column
    @Type(type = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;


    public SMSPayment(){}
    public SMSPayment(String code, Position position, User user, Player player){
        this.code = code;
        this.position = position;
        this.user = user;
        this.player = player;

        this.date = new Date();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public int compareTo(SMSPayment smsPayment) {
        return smsPayment.getDate().compareTo(getDate());
    }
}
