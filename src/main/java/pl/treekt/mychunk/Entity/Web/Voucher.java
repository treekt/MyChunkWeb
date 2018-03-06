package pl.treekt.mychunk.Entity.Web;

import pl.treekt.mychunk.Entity.Game.Player;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vouchers")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String value;
    private int used;
    private int limit;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "player_voucher",
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<Player> players;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
