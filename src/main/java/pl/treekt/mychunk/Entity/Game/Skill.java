package pl.treekt.mychunk.Entity.Game;

import pl.treekt.mychunk.Utils.Enums.SkillType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "skills")
public class Skill {

  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "type")
  private String type;
  @Column(name = "cooldownLevel")
  private Long cooldownLevel;
  @Column(name = "durationLevel")
  private Long durationLevel;
  @Column(name = "powerLevel")
  private Long powerLevel;
  @Column(name = "nickname")
  private String nickname;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getCooldownLevel() {
    return cooldownLevel;
  }

  public void setCooldownLevel(Long cooldownLevel) {
    this.cooldownLevel = cooldownLevel;
  }

  public Long getDurationLevel() {
    return durationLevel;
  }

  public void setDurationLevel(Long durationLevel) {
    this.durationLevel = durationLevel;
  }

  public Long getPowerLevel() {
    return powerLevel;
  }

  public void setPowerLevel(Long powerLevel) {
    this.powerLevel = powerLevel;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  //Custom
  public SkillType getSkillType(){
    return SkillType.getSkillType(getType());
  }
}
