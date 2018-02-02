package pl.treekt.mychunk.Service.Interfaces;

import pl.treekt.mychunk.Entity.Armor;
import pl.treekt.mychunk.Utils.Enums.ArmorType;

import java.util.List;

public interface IArmorService {
    List<Armor> getAllArmors(String nickname);
    Armor getArmorByType(String nickname, ArmorType type);
    boolean addArmor(Armor armor);
    void updateArmor(Armor armor);
    void deleteArmor(String nickname, ArmorType type);
}
