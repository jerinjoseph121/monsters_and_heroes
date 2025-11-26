package Monster;

import Common.AbstractClasses.Character;
import Common.utils.CharacterType;
import Common.utils.Helper;

public class Monster extends Character{
    double baseDamageValue;
    double defenseValue;
    double dodgeAbilityValue;
    MonsterType monsterType;

    public Monster(String monsterName, int monsterLevel, MonsterType monsterType,
                   double baseDamageValue, double defenseValue, double dodgeAbilityValue) {
        super.id = Helper.generateId();
        super.characterType = CharacterType.MONSTER;
        super.name = monsterName;
        super.level = monsterLevel;
        super.HP = monsterLevel * 100;
        this.baseDamageValue = baseDamageValue;
        this.defenseValue = defenseValue;
        this.dodgeAbilityValue = dodgeAbilityValue;
        this.monsterType = monsterType;
    }

    public int getId() {
        return super.id;
    }

    public CharacterType getType() {
        return super.characterType;
    }

    public String getName() {
        return super.name;
    }

    public int getLevel() {
        return super.level;
    }

    public double getHP() {
        return super.HP;
    }

    public void setHP(double HP) {
        super.HP = HP;
    }

    public double getBaseDamageValue() {
        return baseDamageValue;
    }

    public double getDefenseValue() {
        return defenseValue;
    }

    public double getDodgeAbilityValue() {
        return dodgeAbilityValue;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }
}
