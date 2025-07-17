package se233.chapter1exercise.model.character;

import se233.chapter1exercise.model.DamageType;
import se233.chapter1exercise.model.item.Armor;
import se233.chapter1exercise.model.item.Weapon;

//Imports are omitted
public class BasedCharacter {
    protected String name, imgpath;
    protected DamageType type;
    protected Integer fullHp, basedPow, basedDef, basedRes;
    protected Integer hp, power, defense, resistance;
    protected Weapon weapon;
    protected Armor aromor;
    private Armor armor;

    public String getName() { return name; }
    public String getImagepath() { return imgpath; }
    public Integer getHp() { return hp; }
    public Integer getFullHp() { return fullHp; }
    public Integer getPower() { return power; }
    public Integer setPower(Integer power) { this.power = power; return power; }
    public Integer getDefense() { return defense;}
    public Integer getResistance() { return resistance;}
    public void equipWeapon( Weapon weapon) {
        this.weapon = weapon;
        this.power = this.basedPow + weapon.getPower();
    }
    public void equipArmor( Armor armor){
        this.armor = armor;
        this.defense = this.basedDef + armor.getDefense();
        this.resistance = this.basedRes + armor.getResistance();
    }
    public void unequipArmor( ){
        this.armor = null;
        this.defense = this.basedDef;
        this.resistance = this.basedRes;
    }
    public void unequipWeapon( ) {
        this.weapon = null;
        this.power = this.basedPow;
    }

    @Override
    public String toString() { return name;}
    public DamageType getType() {
        return type;
    }
}