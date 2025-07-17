package se233.chapter1exercise.model.character;

import se233.chapter1exercise.model.DamageType;

public class HeroCharacter extends BasedCharacter {
    public HeroCharacter(String name, String imgpath, int basedDef, int basedRes) {
        this.name = name;
        this.type = DamageType.magical;
        this.imgpath = imgpath;
        this.fullHp = 56;
        this.basedPow = 80;
        this.basedDef = basedDef;
        this.basedRes = basedRes;
        this.hp = this.fullHp;
        this.power = this.basedPow;
        this.defense = basedDef;
        this.resistance = basedRes;
    }
}

