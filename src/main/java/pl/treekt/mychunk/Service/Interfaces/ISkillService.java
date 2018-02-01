package pl.treekt.mychunk.Service.Interfaces;

import pl.treekt.mychunk.Entity.Skill;
import pl.treekt.mychunk.Utils.Enums.SkillType;

import java.util.List;

public interface ISkillService {
    List<Skill> getAllSkills(String nickname);
    Skill getSkillByType(String nickname, SkillType type);
    boolean addSkill(Skill skill);
    void updateSkill(Skill skill);
    void deleteSkill(String nickname, SkillType type);
}
