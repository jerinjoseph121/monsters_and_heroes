package Item;

import Common.AbstractClasses.Item;
import Common.utils.Helper;
import Common.utils.ItemType;

public class Armor extends Item {
    int damageReductionValue;
    boolean isEquipped;

    public Armor(String name, int price, int level, int damageReductionValue,  boolean isEquipped) {
        super.id = Helper.generateId();
        super.itemType = ItemType.ARMOR;
        super.name = name;
        super.price = price;
        super.level = level;
        this.damageReductionValue = damageReductionValue;
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

    public int getDamageReductionValue() {
        return damageReductionValue;
    }

    public boolean isEquipped() {
        return isEquipped;
    }

    public void setEquipped(boolean isEquipped) {
        this.isEquipped = isEquipped;
    }
}
