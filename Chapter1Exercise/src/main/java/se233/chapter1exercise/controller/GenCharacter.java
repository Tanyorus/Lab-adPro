package se233.chapter1exercise.controller;

import se233.chapter1exercise.model.character.BasedCharacter;
import se233.chapter1exercise.model.character.MagicalCharacter;
import se233.chapter1exercise.model.character.PhysicalCharacter;
import se233.chapter1exercise.model.character.Battlemage;

import java.util.Random;

public class GenCharacter{
    public static BasedCharacter setUpCharacter(){
        BasedCharacter character;
        Random rand = new Random();
        int type = rand.nextInt(4);
        int basedDef = rand.nextInt(50)+1;
        int basedRes = rand.nextInt(50)+1;
        if (type == 1 ) {
            character = new MagicalCharacter("MagicChar1", "assets/wizard.png",
                    basedDef,basedRes);
        } else if (type == 2 ) {
            character = new PhysicalCharacter("PhysicalChar1", "assets/knight.png",
                    basedDef, basedRes);
        }else if (type == 3 )  {
                character = new PhysicalCharacter("Battlemage", "assets/tank.png",
                        basedDef,basedRes);
        } else {
            character = new Battlemage("Hero", "assets/hero.png",
                    basedDef,basedRes);
        }
        return character;
    }
}
