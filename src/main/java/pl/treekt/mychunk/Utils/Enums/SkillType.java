package pl.treekt.mychunk.Utils.Enums;

public enum SkillType {
    Standard(false, false, false),
    Spowolnienie(true, true, true),
    Trucizna(true, true, true),
    Ogien(true, true, false),
    Piorun(true, false, false),
    Lasso(true, false, true),
    Multishot(true, false, true),
    Oslepienie(true, true, true),
    Zamrozenie(true, true, false),
    Eksplozja(true, false, true),
    Wymioty(true, true, true),
    Knockback(true, false, true),
    Teleport(true, false, false),
    Wither(true, true, true),
    Jajo(false, false, false),
    Sniezka(false, false, false);

    private boolean cooldown;
    private boolean duration;
    private boolean power;

    public boolean isCooldown() {
        return cooldown;
    }

    public boolean isDuration() {
        return duration;
    }

    public boolean isPower() {
        return power;
    }

    SkillType(boolean cooldownExists, boolean durationExists, boolean powerExists){
        this.cooldown = cooldownExists;
        this.duration = durationExists;
        this.power = powerExists;
    }

    public static SkillType getSkillType(String type){
        for(SkillType SkillType : SkillType.values()){
            if(type.equals(SkillType.toString())){
                return SkillType;
            }
        }
        return null;
    }
}
