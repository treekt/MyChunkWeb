package pl.treekt.mychunk.Dao;

import org.springframework.stereotype.Repository;
import pl.treekt.mychunk.Dao.Interfaces.ISkillDao;
import pl.treekt.mychunk.Entity.Game.Skill;
import pl.treekt.mychunk.Utils.Enums.SkillType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class SkillDao implements ISkillDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Skill> getAllSkills(String nickname) {
        String query =  "FROM Skill WHERE nickname = '" + nickname + "'";
        return (List<Skill>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public Skill getSkillByType(String nickname, SkillType type) {
        String query = "FROM Skill WHERE nickname = '" + nickname + "' AND type = '" + type.toString() + "'";
        return (Skill) entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public void addSkill(Skill skill) {
        entityManager.persist(skill);
    }

    @Override
    public void updateSkill(Skill skill) {
        Skill newSkill = new Skill();
        newSkill.setType(skill.getType());
        newSkill.setCooldownLevel(skill.getCooldownLevel());
        newSkill.setDurationLevel(skill.getDurationLevel());
        newSkill.setPowerLevel(skill.getPowerLevel());
        newSkill.setNickname(skill.getNickname());

        entityManager.flush();
    }

    @Override
    public void deleteSkill(String nickname, SkillType type) {
        entityManager.remove(getSkillByType(nickname, type));
    }

    @Override
    public boolean skillExists(String nickname, SkillType type) {
        return getSkillByType(nickname, type) != null ? true : false;
    }
}
