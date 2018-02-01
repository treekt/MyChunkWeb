package pl.treekt.mychunk.Utils.Enums;

public enum SkillType {
    Standard("STANDARD"),
    Spowolnienie("WEB"),
    Trucizna("FERMENTED_SPIDER_EYE"),
    Ogien("BLAZE_POWDER"),
    Piorun("NETHER_STAR"),
    Lasso("LEASH"),
    Multishot("MELON_SEEDS"),
    Oslepienie("COAL"),
    Zamrozenie("ICE"),
    Eksplozja("FIREBALL"),
    Wymioty("CHORUS_FRUIT_POPPED"),
    Knockback("GOLDEN_APPLE"),
    Teleport("ENDER_PEARL"),
    Wither("EYE_OF_ENDER"),
    Jajo("EGG"),
    Sniezka("SNOW_BALL");

    private String iconName;

    SkillType(String iconName){
        this.iconName = iconName;
    }

    public String getIconName() {
        return iconName;
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
