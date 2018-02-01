package pl.treekt.mychunk.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class User {

  @Id
  @Column(name = "nickname")
  private String nickname;
  @Column(name = "kills")
  private Long kills;
  @Column(name = "deaths")
  private Long deaths;
  @Column(name = "assists")
  private Long assists;
  @Column(name = "tokens")
  private Long tokens;
  @Column(name = "last_online")
  private String lastOnline;

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public Long getKills() {
    return kills;
  }

  public void setKills(Long kills) {
    this.kills = kills;
  }

  public Long getDeaths() {
    return deaths;
  }

  public void setDeaths(Long deaths) {
    this.deaths = deaths;
  }

  public Long getAssists() {
    return assists;
  }

  public void setAssists(Long assists) {
    this.assists = assists;
  }

  public Long getTokens() {
    return tokens;
  }

  public void setTokens(Long tokens) {
    this.tokens = tokens;
  }

  public String getLastOnline() {
    return lastOnline;
  }

  public void setLastOnline(String lastOnline) {
    this.lastOnline = lastOnline;
  }

  //Custom

  public double getKD(){

    double kd = (double)kills/(double)(deaths+1);
    if(deaths != 0){
      kd = (double)kills/(double)deaths;
    }
    return new BigDecimal(kd).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
  }
}
