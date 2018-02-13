package pl.treekt.mychunk.Utils.Enums;

public enum ArmorType {
    LEATHER_HELMET,
    LEATHER_CHESTPLATE,
    LEATHER_LEGGINGS,
    LEATHER_BOOTS,
    CHAINMAIL_HELMET,
    CHAINMAIL_CHESTPLATE,
    CHAINMAIL_LEGGINGS,
    CHAINMAIL_BOOTS,
    GOLD_HELMET,
    GOLD_CHESTPLATE,
    GOLD_LEGGINGS,
    GOLD_BOOTS,
    IRON_HELMET,
    IRON_CHESTPLATE,
    IRON_LEGGINGS,
    IRON_BOOTS,
    DIAMOND_HELMET,
    DIAMOND_CHESTPLATE,
    DIAMOND_LEGGINGS,
    DIAMOND_BOOTS;

    private int weight;

    ArmorType(){

        if(this.toString().contains("LEATHER")){
            weight = 0;
        }else if(this.toString().contains("CHAINMAIL")){
            weight = 10;
        }else if(this.toString().contains("GOLD")){
            weight = 20;
        }else if(this.toString().contains("IRON")){
            weight = 30;
        }else if(this.toString().contains("DIAMOND")){
            weight = 40;
        }

        if(this.toString().toString().contains("HELMET")){
            weight += 1;
        }else if(this.toString().contains("CHESTPLATE")){
            weight += 2;
        }else if(this.toString().contains("LEGGINGS")){
            weight += 3;
        }else if(this.toString().contains("BOOTS")){
            weight += 4;
        }
    }

    public static ArmorType getArmorType(String name){
        for(ArmorType armorType : ArmorType.values()){
            if(name.equals(armorType.toString())){
                return armorType;
            }
        }
        return null;
    }

    public int getWeight() {
        return weight;
    }

}
