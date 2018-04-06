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
  private int active;
  @Column(name = "protection")
  private int protection;
  @Column(name = "fire_protection")
  private int fireProtection;
  @Column(name = "blast_protection")
  private int blastProtection;
  @Column(name = "projectile_protection")
  private int projectileProtection;
  @Column(name = "nickname")
  private String nickname;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }

  public int getProtection() {
    return protection;
  }

  public void setProtection(int protection) {
    this.protection = protection;
  }

  public int getFireProtection() {
    return fireProtection;
  }

  public void setFireProtection(int fireProtection) {
    this.fireProtection = fireProtection;
  }

  public int getBlastProtection() {
    return blastProtection;
  }

  public void setBlastProtection(int blastProtection) {
    this.blastProtection = blastProtection;
  }

  public int getProjectileProtection() {
    return projectileProtection;
  }

  public void setProjectileProtection(int projectileProtection) {
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
