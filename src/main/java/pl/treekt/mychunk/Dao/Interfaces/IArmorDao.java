package pl.treekt.mychunk.Dao.Interfaces;

import pl.treekt.mychunk.Entity.Game.Armor;
import pl.treekt.mychunk.Utils.Enums.ArmorType;

import java.util.List;

public interface IArmorDao {
    List<Armor> getAllArmors(String nickname);
    Armor getArmorByType(String nickname, ArmorType type);
    void addArmor(Armor armor);
    void updateArmor(Armor armor);
    void deleteArmor(String nickname, ArmorType type);
    boolean armorExists(String nickname, ArmorType type);
}
