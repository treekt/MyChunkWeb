package pl.treekt.mychunk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.Dao.Interfaces.ISkillDao;
import pl.treekt.mychunk.Entity.Game.Skill;
import pl.treekt.mychunk.Service.Interfaces.ISkillService;
import pl.treekt.mychunk.Utils.Enums.SkillType;

import java.util.List;

@Service
public class SkillService implements ISkillService{

    @Autowired
    private ISkillDao skillDao;

    @Override
    public List<Skill> getAllSkills(String nickname) {
        return skillDao.getAllSkills(nickname);
    }

    @Override
    public Skill getSkillByType(String nickname, SkillType type) {
        return skillDao.getSkillByType(nickname, type);
    }

    @Override
    public boolean addSkill(Skill skill) {
        if(skillDao.skillExists(skill.getNickname(), SkillType.getSkillType(skill.getType()))){
            return false;
        }else{
            skillDao.addSkill(skill);
            return true;
        }
    }

    @Override
    public void updateSkill(Skill skill) {
        skillDao.updateSkill(skill);
    }

    @Override
    public void deleteSkill(String nickname, SkillType type) {
        skillDao.deleteSkill(nickname, type);
    }
}
