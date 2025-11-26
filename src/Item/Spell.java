package Item;

import Common.AbstractClasses.Item;
import Common.utils.Helper;
import Common.utils.ItemType;
import Common.utils.SpellType;

public class Spell extends Item {
    SpellType spellType;
    int damageValue;
    int manaCost;

    public Spell(String name, int price, int level, SpellType spellType, int damageValue, int manaCost) {
        super.id = Helper.generateId();
        super.itemType = ItemType.SPELL;
        super.name = name;
        super.price = price;
        super.level = level;
        this.spellType = spellType;
        this.damageValue = damageValue;
        this.manaCost = manaCost;
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

    public SpellType getSpellType() {
        return spellType;
    }

    public int getDamageValue() {
        return damageValue;
    }

    public int getManaCost() {
        return manaCost;
    }
}
