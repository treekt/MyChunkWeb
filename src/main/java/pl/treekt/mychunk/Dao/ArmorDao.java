package pl.treekt.mychunk.Dao;

import org.springframework.stereotype.Repository;
import pl.treekt.mychunk.Dao.Interfaces.IArmorDao;
import pl.treekt.mychunk.Entity.Game.Armor;
import pl.treekt.mychunk.Utils.Enums.ArmorType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class ArmorDao implements IArmorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Armor> getAllArmors(String nickname) {
        String query = "FROM Armor WHERE nickname = '" + nickname + "'";
        return (List<Armor>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public Armor getArmorByType(String nickname, ArmorType type) {
        String query = "FROM Armor WHERE nickname = '" + nickname + "' AND name = '" + type.toString() + "'";
        return (Armor) entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public void addArmor(Armor armor) {
        entityManager.persist(armor);
    }

    @Override
    public void updateArmor(Armor armor) {
        entityManager.flush();
    }

    @Override
    public void deleteArmor(String nickname, ArmorType type) {
        entityManager.remove(getArmorByType(nickname, type));
    }

    @Override
    public boolean armorExists(String nickname, ArmorType type) {
        return getArmorByType(nickname, type) != null ? true : false;
    }
}
