package pl.treekt.mychunk.Entity.Web;

import pl.treekt.mychunk.Entity.Game.Player;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vouchers")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    private int max;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "player_voucher",
            joinColumns = @JoinColumn(name = "voucher_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private Set<Player> players;

    public Voucher(){}
    public Voucher(Voucher voucher){
        this.id = voucher.id;
        this.code = voucher.code;
        this.max = voucher.max;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
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
