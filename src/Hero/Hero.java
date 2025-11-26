package Hero;

import Common.AbstractClasses.Character;
import Common.Catalog.ItemCatalog.WeaponCatalog;
import Common.utils.CharacterType;
import Common.utils.Helper;
import Inventory.Inventory;
import Item.Weapon;

public class Hero extends Character {
    double MP;
    int strengthValue;
    int dexterityValue;
    int agilityValue;
    int totalGold;
    int experiencePoints;
    Inventory inventory;
    HeroType heroType;
    Weapon equippedWeapon;

    public Hero(String heroName, HeroType heroType, int mana, int strengthValue, int dexterityValue,
                int agilityValue, int startingMoney, int startingExperience) {
        super.id = Helper.generateId();
        super.characterType = CharacterType.HERO;
        super.name = heroName;
        super.level = 1;
        super.HP = 100;
        MP = mana;
        this.strengthValue = strengthValue;
        this.dexterityValue = dexterityValue;
        this.agilityValue = agilityValue;
        totalGold = startingMoney;
        experiencePoints = startingExperience;
        inventory = new Inventory(this);
        this.heroType = heroType;
        WeaponCatalog baseWeapon = WeaponCatalog.SWORD;
        Weapon starterWeapon = new Weapon(baseWeapon.getName(), baseWeapon.getCost(),
                baseWeapon.getLevel(), baseWeapon.getDamage(), baseWeapon.getRequiredHands(), true);
        this.equippedWeapon = starterWeapon;
        inventory.addItem(starterWeapon);
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

    public double getMP() {
        return MP;
    }

    public int getStrengthValue() {
        return  strengthValue;
    }

    public int getDexterityValue() {
        return dexterityValue;
    }

    public int getAgilityValue() {
        return agilityValue;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public HeroType getHeroType() {
        return heroType;
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }
}
