package pl.treekt.mychunk.Dao.Interfaces;

import pl.treekt.mychunk.Entity.Game.Skill;
import pl.treekt.mychunk.Utils.Enums.SkillType;

import java.util.List;

public interface ISkillDao {
    List<Skill> getAllSkills(String nickname);
    Skill getSkillByType(String nickname, SkillType type);
    void addSkill(Skill skill);
    void updateSkill(Skill skill);
    void deleteSkill(String nickname, SkillType type);
    boolean skillExists(String nickname, SkillType type);
}
