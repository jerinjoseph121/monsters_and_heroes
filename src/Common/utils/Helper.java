package Common.utils;

import Common.AbstractClasses.Item;
import Common.Catalog.ItemCatalog.ArmorCatalog;
import Common.Catalog.ItemCatalog.PotionCatalog;
import Common.Catalog.ItemCatalog.SpellCatalog.FireSpellCatalog;
import Common.Catalog.ItemCatalog.SpellCatalog.IceSpellCatalog;
import Common.Catalog.ItemCatalog.SpellCatalog.LightningSpellCatalog;
import Common.Catalog.ItemCatalog.WeaponCatalog;
import Hero.Hero;
import Inventory.Inventory;
import Item.Armor;
import Item.Potion;
import Item.Spell;
import Item.Weapon;
import Player.Player;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Helper {
    private static int nextId = 1;

    public static synchronized int generateId() {
        return nextId++;
    }



    // displays hero details
    public static void allHeroesStats(ArrayList<Hero> heroes) {
        for (int i = 0; i < heroes.size(); i++) {
            System.out.printf("===== Hero %d : %s =====\n", i, heroes.get(i).getName());
            System.out.printf("Name: %s\n", heroes.get(i).getName());
            System.out.printf("Level: %d\n", heroes.get(i).getLevel());
            switch (heroes.get(i).getHeroType()) {
                case WARRIOR:
                    System.out.println("Hero Type: Warrior");
                    break;
                case SORCERER:
                    System.out.println("Hero Type: Sorcerer");
                    break;
                case PALADIN:
                    System.out.println("Hero Type: Paladin");
                    break;
                default:
                    System.out.println("Hero Type: None");
            }
            System.out.printf("HP: %.2f/%.2f\n", heroes.get(i).getHP(), heroes.get(i).getHP_MAX());
            System.out.printf("MP: %.2f/%.2f\n", heroes.get(i).getMP(), heroes.get(i).getMP_MAX());
            System.out.printf("Strength: %.2f\n", heroes.get(i).getStrengthValue());
            System.out.printf("Defense: %.2f\n", heroes.get(i).getDefenseValue());
            System.out.printf("Agility: %.2f\n", heroes.get(i).getAgilityValue());
            System.out.printf("Dexterity: %.2f\n", heroes.get(i).getDexterityValue());
            System.out.printf("Total Gold: %d\n", heroes.get(i).getTotalGold());
            System.out.printf("Experience Points: %d/%d\n", heroes.get(i).getExperiencePoints(),
                    (heroes.get(i).getLevel() * 10));
            showHeroInventory(heroes.get(i));
        }
    }

    // Displays hero inventory details
    public static void showHeroInventory(Hero hero) {
        System.out.printf("--- Inventory of %s ---\n", hero.getName());
        Inventory inventory = hero.getInventory();

        if (inventory == null) {
            return;
        }

        ArrayList<Weapon> weapons = inventory.getWeapons();
        if (!(weapons == null || weapons.isEmpty())) {
            System.out.println("Weapons:");
            for (Weapon weapon : weapons) {
                System.out.printf("Name: %s | Cost: %d | Level: %d | Damage: %d | Required Hands: %d | isEquipped: %b\n",
                        weapon.getName(), weapon.getPrice(), weapon.getLevel(),
                        weapon.getDamageValue(), weapon.getRequiredHandNum(), weapon.isEquipped());
            }
        }

        ArrayList<Armor> armors = inventory.getArmors();
        if (!(armors == null || armors.isEmpty())) {
            System.out.println("Armors:");
            for (Armor armor : armors) {
                System.out.printf("Name: %s | Cost: %d | Level: %d | Damage Reduction: %d | isEquipped: %b\n",
                        armor.getName(), armor.getPrice(), armor.getLevel(),
                        armor.getDamageReductionValue(), armor.isEquipped());
            }
        }

        ArrayList<Potion> potions = inventory.getPotions();
        if (!(potions == null || potions.isEmpty())) {
            System.out.println("Potions:");
            for(Potion potion : potions) {
                System.out.printf("Name: %s | Cost: %d | Level: %d | Attribute Increase: %d | ",
                        potion.getName(), potion.getPrice(), potion.getLevel(),
                        potion.getEffectAmountValue());
                System.out.print("Attributes Effected: [");
                for (int i = 0; i < potion.getAttributes().size(); i++) {
                    if (i == potion.getAttributes().size() - 1) {
                        System.out.print(potion.getAttributes().get(i).name());
                    } else {
                        System.out.print(potion.getAttributes().get(i).name() + " | ");
                    }
                }
                System.out.println("]");
            }
        }

        ArrayList<Spell> spells = inventory.getSpells();
        if (!(spells == null || spells.isEmpty())) {
            System.out.println("Spells:");
            ArrayList<Spell> iceSpells = inventory.getIceSpells();
            if (!(iceSpells == null || iceSpells.isEmpty())) {
                System.out.println("Ice Spells:");
                for (Spell iceSpell : iceSpells) {
                    System.out.printf("Name: %s | Cost: %d | Level: %d | Damage: %d | Mana Cost: %d \n",
                            iceSpell.getName(), iceSpell.getPrice(), iceSpell.getLevel(), iceSpell.getDamageValue(),
                            iceSpell.getManaCost());
                }
            }
            ArrayList<Spell> fireSpells = inventory.getFireSpells();
            if (!(fireSpells == null || fireSpells.isEmpty())) {
                System.out.println("Fire Spells:");
                for (Spell fireSpell : fireSpells) {
                    System.out.printf("Name: %s | Cost: %d | Level: %d | Damage: %d | Mana Cost: %d \n",
                            fireSpell.getName(), fireSpell.getPrice(), fireSpell.getLevel(), fireSpell.getDamageValue(),
                            fireSpell.getManaCost());
                }
            }
            ArrayList<Spell> lightningSpells = inventory.getLightningSpells();
            if (!(lightningSpells == null || lightningSpells.isEmpty())) {
                System.out.println("Lightning Spells:");
                for (Spell lightningSpell : lightningSpells) {
                    System.out.printf("Name: %s | Cost: %d | Level: %d | Damage: %d | Mana Cost: %d \n",
                            lightningSpell.getName(), lightningSpell.getPrice(), lightningSpell.getLevel(),
                            lightningSpell.getDamageValue(), lightningSpell.getManaCost());
                }
            }
        }
    }

    // Displays player stats including hero details and inventory
    public static void showPlayerStats (Player player) {
        System.out.println("========= PLAYER STATS =========");
        System.out.printf("Player Name: %s\n", player.getPlayerName());
        System.out.printf("Hero Count: %d\n", player.getHeroCount());
        System.out.printf("Max Hero Level: %d\n", player.getHeroMaxLevel());
        System.out.printf("Total Hero Level: %d\n", player.getTotalHeroLevel());

        allHeroesStats(player.getHeroes());
    }

    // Used to get random items for market
    public static ArrayList<Item> getRandomItems() {
        ArrayList<Item> items = new ArrayList<>();
        Random rand = new Random();

        // Adding random number of weapons of random type
        int totalWeaponCount = WeaponCatalog.count;
        int requiredWeaponCount = rand.nextInt(totalWeaponCount/2);
        for (int i = 0; i < requiredWeaponCount; i++) {
            int weaponId = rand.nextInt(totalWeaponCount);
            WeaponCatalog weaponCatalog = WeaponCatalog.fromTypeId(weaponId);
            Weapon weapon = new Weapon(weaponCatalog.getName(), weaponCatalog.getCost(), weaponCatalog.getLevel(),
                    weaponCatalog.getDamage(), weaponCatalog.getRequiredHands(), false);

            items.add(weapon);
        }

        // Adding random number of armors of random type
        int totalArmorCount = ArmorCatalog.count;
        int requiredArmorCount = rand.nextInt(totalArmorCount/2);
        for (int i = 0; i < requiredArmorCount; i++) {
            int armorId = rand.nextInt(totalArmorCount);
            ArmorCatalog armorCatalog = ArmorCatalog.fromTypeId(armorId);
            Armor armor = new Armor(armorCatalog.getName(), armorCatalog.getCost(), armorCatalog.getLevel(),
                    armorCatalog.getDamageReduced(), false);

            items.add(armor);
        }

        // Adding random number of potions of random type
        int totalPotionCount = PotionCatalog.count;
        int requiredPotionCount = rand.nextInt(totalPotionCount/2);
        for (int i = 0; i < requiredPotionCount; i++) {
            int potionId = rand.nextInt(totalPotionCount);
            PotionCatalog potionCatalog = PotionCatalog.fromTypeId(potionId);
            Potion potion = new Potion(potionCatalog.getName(), potionCatalog.getCost(), potionCatalog.getLevel(),
                    potionCatalog.getAttributeIncrease(), new ArrayList<>(potionCatalog.getAttributes()));
            items.add(potion);
        }

        // Adding random number of ice spells of random type
        int totalIceSpellCount = IceSpellCatalog.count;
        int requiredIceSpellCount = rand.nextInt(totalIceSpellCount/2);
        for (int i = 0; i < requiredIceSpellCount; i++) {
            int iceSpellId = rand.nextInt(totalIceSpellCount);
            IceSpellCatalog iceSpellCatalog = IceSpellCatalog.fromTypeId(iceSpellId);
            Spell iceSpell = new Spell(iceSpellCatalog.getName(), iceSpellCatalog.getCost(),
                    iceSpellCatalog.getRequiredLevel(), SpellType.ICE, iceSpellCatalog.getDamage(),
                    iceSpellCatalog.getManaCost());
            items.add(iceSpell);
        }

        // Adding random number of fire spells of random type
        int totalFireSpellCount = FireSpellCatalog.count;
        int requiredFireSpellCount = rand.nextInt(totalFireSpellCount/2);
        for (int i = 0; i < requiredFireSpellCount; i++) {
            int fireSpellId = rand.nextInt(totalFireSpellCount);
            FireSpellCatalog fireSpellCatalog = FireSpellCatalog.fromTypeId(fireSpellId);
            Spell fireSpell = new Spell(fireSpellCatalog.getName(), fireSpellCatalog.getCost(),
                    fireSpellCatalog.getRequiredLevel(), SpellType.FIRE, fireSpellCatalog.getDamage(),
                    fireSpellCatalog.getManaCost());
            items.add(fireSpell);
        }

        // Adding random number of lightning spells of random type
        int totalLightningSpellCount = LightningSpellCatalog.count;
        int requiredLightningSpellCount = rand.nextInt(totalLightningSpellCount/2);
        for (int i = 0; i < requiredLightningSpellCount; i++) {
            int lightningSpellId = rand.nextInt(totalLightningSpellCount);
            LightningSpellCatalog lightningSpellCatalog = LightningSpellCatalog.fromTypeId(lightningSpellId);
            Spell lightningSpell = new Spell(lightningSpellCatalog.getName(), lightningSpellCatalog.getCost(),
                    lightningSpellCatalog.getRequiredLevel(), SpellType.LIGHTNING, lightningSpellCatalog.getDamage(),
                    lightningSpellCatalog.getManaCost());
            items.add(lightningSpell);
        }

        return items;
    }

    // ===== INPUT HELPERS =====
    // These help avoid Scanner nextInt/nextLine issues and cut down duplicate code

    public static int readIntInRange(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int v = Integer.parseInt(line);
                if (v < min || v > max) {
                    System.out.printf("Please enter a number between %d and %d.%n", min, max);
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    public static int readIntInRangeWithDefault(
            Scanner sc, String prompt, int min, int max, int defaultVal
    ) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                return defaultVal;
            }
            try {
                int v = Integer.parseInt(line);
                if (v < min || v > max) {
                    System.out.printf("Please enter a number between %d and %d.%n", min, max);
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    public static double readDoubleInRangeWithDefault(
            Scanner sc, String prompt, double min, double max, double defaultVal
    ) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                return defaultVal;
            }
            try {
                double v = Double.parseDouble(line);
                if (v < min || v > max) {
                    System.out.printf("Please enter a number between %.2f and %.2f.%n", min, max);
                    continue;
                }
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
            }
        }
    }
}
