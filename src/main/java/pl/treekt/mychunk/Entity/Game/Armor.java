package pl.treekt.mychunk.Entity.Game;

import pl.treekt.mychunk.Utils.Enums.ArmorType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "armors")
public class Armor implements Comparable<Armor>{

  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "name")
  private String name;
  @Column(name = "active")
  private Long active;
  @Column(name = "protection")
  private String protection;
  @Column(name = "fire_protection")
  private String fireProtection;
  @Column(name = "blast_protection")
  private String blastProtection;
  @Column(name = "projectile_protection")
  private String projectileProtection;
  @Column(name = "nickname")
  private String nickname;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getActive() {
    return active;
  }

  public void setActive(Long active) {
    this.active = active;
  }

  public String getProtection() {
    return protection;
  }

  public void setProtection(String protection) {
    this.protection = protection;
  }

  public String getFireProtection() {
    return fireProtection;
  }

  public void setFireProtection(String fire_protection) {
    this.fireProtection = fire_protection;
  }

  public String getBlastProtection() {
    return blastProtection;
  }

  public void setBlastProtection(String blast_protection) {
    this.blastProtection = blast_protection;
  }

  public String getProjectileProtection() {
    return projectileProtection;
  }

  public void setProjectileProtection(String projectileProtection) {
    this.projectileProtection = projectileProtection;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  @Override
  public int compareTo(Armor o) {
    ArmorType thisArmor = ArmorType.getArmorType(name);
    ArmorType newArmor = ArmorType.getArmorType(o.getName());
    return (thisArmor.getWeight() - newArmor.getWeight());
  }
}
