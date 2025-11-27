package Hero;

import Common.AbstractClasses.Character;
import Common.Catalog.ItemCatalog.WeaponCatalog;
import Common.utils.CharacterType;
import Common.utils.Helper;
import Inventory.Inventory;
import Item.Armor;
import Item.Weapon;

public class Hero extends Character {
    // Core stats
    private double MP;
    private double MP_MAX;
    private double HP_MAX;

    private double strengthValue;
    private double dexterityValue;
    private double agilityValue;
    private double defenseValue;

    // Progression
    private int totalGold;
    private int experiencePoints;

    // Composition
    private Inventory inventory;
    private HeroType heroType;
    private Weapon equippedWeapon;
    private Armor equippedArmor;

    /**
     * Main hero constructor used throughout the game.
     *
     * @param heroName            name of the hero
     * @param heroType            type of hero (WARRIOR, SORCERER, PALADIN)
     * @param mana                starting mana
     * @param strengthValue       starting strength
     * @param dexterityValue      starting dexterity
     * @param agilityValue        starting agility
     * @param startingMoney       initial gold
     * @param startingExperience  initial experience points
     */
    public Hero(String heroName,
                HeroType heroType,
                int mana,
                double strengthValue,
                double dexterityValue,
                double agilityValue,
                int startingMoney,
                int startingExperience) {

        // Base character fields
        super.id = Helper.generateId();
        super.characterType = CharacterType.HERO;
        super.name = heroName;
        super.level = 1;
        super.HP = 100;

        // Hero-specific stats
        this.HP_MAX = 100;
        this.MP = mana;
        this.MP_MAX = mana;
        this.strengthValue = strengthValue;
        this.dexterityValue = dexterityValue;
        this.agilityValue = agilityValue;
        this.defenseValue = 0;

        this.totalGold = startingMoney;
        this.experiencePoints = startingExperience;

        this.inventory = new Inventory(this);
        this.heroType = heroType;

        // Give a basic starter weapon
        WeaponCatalog baseWeapon = WeaponCatalog.SWORD;
        Weapon starterWeapon = new Weapon(
                baseWeapon.getName(),
                baseWeapon.getCost(),
                baseWeapon.getLevel(),
                baseWeapon.getDamage(),
                baseWeapon.getRequiredHands(),
                true
        );
        this.equippedWeapon = starterWeapon;
        this.equippedArmor = null;
        this.inventory.addItem(starterWeapon);
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

    /**
     * Simple level-up used by Battle/Helper.
     * Stat scaling is handled externally (Battle.updatePlayerStats).
     */
    public void levelUp() {
        super.level += 1;
    }

    @Override
    public double getHP() {
        return super.HP;
    }

    @Override
    public void setHP(double HP) {
        super.HP = HP;
    }

    // ========== HP / MP ==========

    public double getHP_MAX() {
        return HP_MAX;
    }

    public void setHP_MAX(double HP_MAX) {
        this.HP_MAX = HP_MAX;
    }

    public double getMP() {
        return MP;
    }

    public void setMP(double MP) {
        this.MP = MP;
    }

    public double getMP_MAX() {
        return MP_MAX;
    }

    public void setMP_MAX(double MP_MAX) {
        this.MP_MAX = MP_MAX;
    }

    // ========== Core attributes ==========

    public double getStrengthValue() {
        return strengthValue;
    }

    public void setStrengthValue(double strengthValue) {
        this.strengthValue = strengthValue;
    }

    public double getDexterityValue() {
        return dexterityValue;
    }

    public void setDexterityValue(double dexterityValue) {
        this.dexterityValue = dexterityValue;
    }

    public double getAgilityValue() {
        return agilityValue;
    }

    public void setAgilityValue(double agilityValue) {
        this.agilityValue = agilityValue;
    }

    public double getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(double defenseValue) {
        this.defenseValue = defenseValue;
    }

    // ========== Gold / XP ==========

    public int getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(int totalGold) {
        this.totalGold = totalGold;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    // ========== Composition ==========

    public Inventory getInventory() {
        return inventory;
    }

    public HeroType getHeroType() {
        return heroType;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Armor equippedArmor) {
        this.equippedArmor = equippedArmor;
    }
}
