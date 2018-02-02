package pl.treekt.mychunk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.Dao.Interfaces.IArmorDao;
import pl.treekt.mychunk.Entity.Armor;
import pl.treekt.mychunk.Service.Interfaces.IArmorService;
import pl.treekt.mychunk.Utils.Enums.ArmorType;

import java.util.List;

@Service
public class ArmorService implements IArmorService{

    @Autowired
    private IArmorDao armorDao;

    @Override
    public List<Armor> getAllArmors(String nickname) {
        return armorDao.getAllArmors(nickname);
    }

    @Override
    public Armor getArmorByType(String nickname, ArmorType type) {
        return armorDao.getArmorByType(nickname, type);
    }

    @Override
    public boolean addArmor(Armor armor) {
        if(armorDao.armorExists(armor.getNickname(), ArmorType.getArmorType(armor.getName()))){
            return false;
        }else{
            armorDao.addArmor(armor);
            return true;
        }
    }

    @Override
    public void updateArmor(Armor armor) {
        armorDao.updateArmor(armor);
    }

    @Override
    public void deleteArmor(String nickname, ArmorType type) {
        armorDao.deleteArmor(nickname, type);
    }
}
