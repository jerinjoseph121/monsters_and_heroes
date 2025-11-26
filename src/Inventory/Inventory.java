package Inventory;

import Common.AbstractClasses.Item;
import Common.utils.SpellType;
import Item.Spell;
import Item.Weapon;
import Item.Armor;
import Item.Potion;
import Common.utils.ItemType;
import Hero.Hero;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Item> inventoryItems;
    Hero owner;

    public Inventory(Hero hero) {
        inventoryItems = new ArrayList<>();
        owner = hero;
    }

    public ArrayList<Item> getInventoryItems() {
        return inventoryItems;
    }

    public void addItem(Item item) {
        inventoryItems.add(item);
    }

    public void removeItem(Item item) {
        inventoryItems.remove(item);
    }

    public ArrayList<Spell> getSpells() {
        ArrayList<Spell> spells = new ArrayList<>();
        for (Item item : inventoryItems) {
            if (item.getItemType() == ItemType.SPELL) {
                spells.add((Spell) item);
            }
        }
        return spells;
    }

    public ArrayList<Spell> getFireSpells() {
        ArrayList<Spell> spells = getSpells();
        ArrayList<Spell> fireSpells = new ArrayList<>();
        for (Spell spell : spells) {
            if (spell.getSpellType() == SpellType.FIRE) {
                fireSpells.add(spell);
            }
        }
        return fireSpells;
    }

    public ArrayList<Spell> getIceSpells() {
        ArrayList<Spell> spells = getSpells();
        ArrayList<Spell> iceSpells = new ArrayList<>();
        for (Spell spell : spells) {
            if (spell.getSpellType() == SpellType.ICE) {
                iceSpells.add(spell);
            }
        }
        return iceSpells;
    }

    public ArrayList<Spell> getLightningSpells() {
        ArrayList<Spell> spells = getSpells();
        ArrayList<Spell> lightningSpells = new ArrayList<>();
        for (Spell spell : spells) {
            if (spell.getSpellType() == SpellType.LIGHTNING) {
                lightningSpells.add(spell);
            }
        }
        return lightningSpells;
    }

    public ArrayList<Potion> getPotions() {
        ArrayList<Potion> potions = new ArrayList<>();
        for (Item item : inventoryItems) {
            if (item.getItemType() == ItemType.POTION) {
                potions.add((Potion) item);
            }
        }
        return potions;
    }

    public ArrayList<Armor> getArmors() {
        ArrayList<Armor> armors = new ArrayList<>();
        for (Item item : inventoryItems) {
            if (item.getItemType() == ItemType.ARMOR) {
                armors.add((Armor) item);
            }
        }
        return armors;
    }

    public ArrayList<Weapon> getWeapons() {
        ArrayList<Weapon> weapons = new ArrayList<>();
        for (Item item : inventoryItems) {
            if (item.getItemType() == ItemType.WEAPON) {
                weapons.add((Weapon) item);
            }
        }
        return weapons;
    }
}
