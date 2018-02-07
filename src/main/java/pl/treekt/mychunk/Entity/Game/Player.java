package pl.treekt.mychunk.Entity.Game;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "players")
public class Player implements Comparable<Player> {

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
  @Column(name = "hit")
  private Long hit;
  @Column(name = "miss")
  private Long miss;
  @Column(name = "join_date")
  private String joinDate;
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

  public Long getHit() {
    return hit;
  }

  public void setHit(Long hit) {
    this.hit = hit;
  }

  public Long getMiss() {
    return miss;
  }

  public void setMiss(Long miss) {
    this.miss = miss;
  }

  public String getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(String joinDate) {
    this.joinDate = joinDate;
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

  public Long getSumOfShots(){
    return hit+miss;
  }

  public double getAccuracy(){
    long shots = hit+miss;
    if(shots == 0){
      return 0;
    }
    double accuracy = (double)hit/(double)shots;
    return new BigDecimal(accuracy * 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  private Date getDateTime() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    Date date = null;
    try {
      date = dateFormat.parse(getJoinDate());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return date;
  }

  @Override
  public int compareTo(Player o) {
    return o.getDateTime().compareTo(getDateTime());
  }
}
