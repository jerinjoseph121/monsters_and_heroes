package Monster;

import Common.AbstractClasses.Character;
import Common.utils.CharacterType;
import Common.utils.Helper;

public class Monster extends Character {
    // Core stats
    private double baseDamageValue;
    private double defenseValue;
    private double dodgeAbilityValue;

    private MonsterType monsterType;

    /**
     * Main monster constructor used by the monster catalogs and Battle.
     *
     * @param monsterName       name
     * @param monsterLevel      level
     * @param monsterType       type (DRAGON, EXOSKELETON, SPIRIT)
     * @param baseDamageValue   base attack damage
     * @param defenseValue      defense value
     * @param dodgeAbilityValue dodge chance value
     */
    public Monster(String monsterName,
                   int monsterLevel,
                   MonsterType monsterType,
                   double baseDamageValue,
                   double defenseValue,
                   double dodgeAbilityValue) {

        super.id = Helper.generateId();
        super.characterType = CharacterType.MONSTER;
        super.name = monsterName;
        super.level = monsterLevel;

        // HP scales with level
        super.HP = monsterLevel * 100;

        this.baseDamageValue = baseDamageValue;
        this.defenseValue = defenseValue;
        this.dodgeAbilityValue = dodgeAbilityValue;
        this.monsterType = monsterType;
    }

    // ========== Abstract Character methods ==========

    @Override
    public int getId() {
        return super.id;
    }

    @Override
    public CharacterType getType() {
        return super.characterType;
    }

    @Override
    public String getName() {
        return super.name;
    }

    @Override
    public int getLevel() {
        return super.level;
    }

    @Override
    public double getHP() {
        return super.HP;
    }

    @Override
    public void setHP(double HP) {
        super.HP = HP;
    }

    // ========== Monster-specific fields ==========

    public double getBaseDamageValue() {
        return baseDamageValue;
    }

    public void setBaseDamageValue(double baseDamageValue) {
        this.baseDamageValue = baseDamageValue;
    }

    public double getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(double defenseValue) {
        this.defenseValue = defenseValue;
    }

    public double getDodgeAbilityValue() {
        return dodgeAbilityValue;
    }

    public void setDodgeAbilityValue(double dodgeAbilityValue) {
        this.dodgeAbilityValue = dodgeAbilityValue;
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }
}
