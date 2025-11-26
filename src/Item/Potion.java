package Item;

import Common.AbstractClasses.Item;
import Common.utils.Helper;
import Common.utils.ItemType;

public class Potion extends Item {
    int effectAmountValue;

    public Potion(String name, int price, int level, int effectAmountValue) {
        super.id = Helper.generateId();
        super.itemType = ItemType.POTION;
        super.name = name;
        super.price = price;
        super.level = level;
        this.effectAmountValue = effectAmountValue;
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

    public int getEffectAmountValue() {
        return effectAmountValue;
    }
}
