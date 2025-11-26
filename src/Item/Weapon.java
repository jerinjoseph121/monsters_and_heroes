package Item;

import Common.AbstractClasses.Item;
import Common.utils.Helper;
import Common.utils.ItemType;

public class Weapon extends Item {
    int damageValue;
    int requiredHandNum;
    boolean isEquipped;

    public Weapon(String name, int price, int level, int damageValue, int requiredHandNum, boolean isEquipped) {
        super.id = Helper.generateId();
        super.itemType = ItemType.WEAPON;
        super.name = name;
        super.price = price;
        super.level = level;
        this.damageValue = damageValue;
        this.requiredHandNum = requiredHandNum;
        this.isEquipped = isEquipped;
    }

    public int getId() {
        return super.id;
    }

    public ItemType getItemType() {
        return super.itemType;
    }
    public String getName() {
        return super.name;
    }

    public int getPrice() {
        return super.price;
    }

    public int getLevel() {
        return super.level;
    }

    public int getDamageValue() {
        return damageValue;
    }

    public int getRequiredHandNum() {
        return requiredHandNum;
    }

    public boolean isEquipped() {
        return isEquipped;
    }

    public void setEquipped(boolean isEquipped) {
        this.isEquipped = isEquipped;
    }
}
