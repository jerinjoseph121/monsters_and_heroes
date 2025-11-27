package Battle;

import Common.Catalog.MonsterCatalog.DragonCatalog;
import Common.Catalog.MonsterCatalog.ExoskeletonCatalog;
import Common.Catalog.MonsterCatalog.SpiritCatalog;
import Common.utils.AttributeType;
import Common.utils.SpellType;
import Hero.Hero;
import Inventory.Inventory;
import Item.Armor;
import Item.Potion;
import Item.Spell;
import Item.Weapon;
import Monster.Monster;
import Monster.MonsterType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Battle {
    private final ArrayList<Hero> heroes;
    private final ArrayList<Monster> monsters;
    private final HashMap<Hero, Monster> rivalMap;
    private static final Random random = new Random();
    private static final double DODGE_CONSTANT = 0.002;

    public Battle(ArrayList<Hero> heroes) {
        this.heroes = heroes;
        this.monsters = new ArrayList<>();
        this.rivalMap = new HashMap<>();
        setupMonsters();
    }

    // ===================== MONSTER SETUP =====================

    private void setupMonsters() {
        int maxHeroLevel = 0;
        for (Hero hero : heroes) {
            if (hero.getLevel() > maxHeroLevel) {
                maxHeroLevel = hero.getLevel();
            }
        }

        int numberOfMonsters = heroes.size();

        while (monsters.size() < numberOfMonsters) {
            int monsterTypeId = random.nextInt(3); // 0: Dragon, 1: Exoskeleton, 2: Spirit
            Monster monster = null;

            switch (monsterTypeId) {
                case 0: {
                    DragonCatalog[] dragons = DragonCatalog.values();
                    DragonCatalog dragon = dragons[random.nextInt(dragons.length)];
                    monster = new Monster(
                            dragon.getName(),
                            dragon.getLevel(),
                            MonsterType.DRAGON,
                            dragon.getBaseDamageValue(),
                            dragon.getDefenseValue(),
                            dragon.getDodgeAbilityValue()
                    );
                    break;
                }
                case 1: {
                    ExoskeletonCatalog[] exoskeletons = ExoskeletonCatalog.values();
                    ExoskeletonCatalog ex = exoskeletons[random.nextInt(exoskeletons.length)];
                    monster = new Monster(
                            ex.getName(),
                            ex.getLevel(),
                            MonsterType.EXOSKELETON,
                            ex.getBaseDamageValue(),
                            ex.getDefenseValue(),
                            ex.getDodgeAbilityValue()
                    );
                    break;
                }
                case 2: {
                    SpiritCatalog[] spirits = SpiritCatalog.values();
                    SpiritCatalog sp = spirits[random.nextInt(spirits.length)];
                    monster = new Monster(
                            sp.getName(),
                            sp.getLevel(),
                            MonsterType.SPIRIT,
                            sp.getBaseDamageValue(),
                            sp.getDefenseValue(),
                            sp.getDodgeAbilityValue()
                    );
                    break;
                }
            }

            if (monster != null &&
                    monster.getLevel() <= maxHeroLevel + 1 &&
                    monster.getLevel() >= maxHeroLevel - 1) {
                monsters.add(monster);
            }
        }

        // Map each hero to a monster (rival)
        for (int i = 0; i < heroes.size(); i++) {
            rivalMap.put(heroes.get(i), monsters.get(i));
        }
    }

    // ===================== BATTLE TRIGGER =====================

    public static boolean isBattleTriggered(double battleProb) {
        double randomNum = random.nextDouble();
        return randomNum <= battleProb;
    }

    // ===================== MAIN BATTLE LOOP =====================

    public boolean arenaFight() {
        Scanner sc = new Scanner(System.in);

        System.out.println("BATTLE STARTS!");

        while (heroesAlive() && monstersAlive()) {
            printStatus();

            // ---------- Heroes' turn ----------
            for (Map.Entry<Hero, Monster> entry : rivalMap.entrySet()) {
                Hero hero = entry.getKey();
                Monster rivalMonster = entry.getValue();

                if (hero.getHP() <= 0) {
                    continue; // dead hero skips turn
                }

                System.out.printf("%n%s's turn%n", hero.getName());

                // Choose action
                int action = askHeroAction(sc, hero);
                switch (action) {
                    case 1: // ATTACK
                        Monster attackTarget = chooseMonsterTarget(sc);
                        if (attackTarget != null) {
                            heroAttackOp(hero, attackTarget);
                        }
                        break;
                    case 2: // CAST SPELL
                        heroCastSpell(sc, hero);
                        break;
                    case 3: // USE POTION
                        heroUsePotion(sc, hero);
                        break;
                    case 4: // EQUIP
                        heroEquipItem(sc, hero);
                        break;
                    default:
                        System.out.println("Skipping turn...");
                        break;
                }

                if (!monstersAlive()) {
                    break;
                }
            }

            if (!monstersAlive()) {
                break;
            }

            // ---------- Monsters' turn ----------
            for (Map.Entry<Hero, Monster> entry : rivalMap.entrySet()) {
                Hero hero = entry.getKey();
                Monster monster = entry.getValue();

                if (monster.getHP() <= 0) {
                    continue; // dead monster
                }
                if (hero.getHP() <= 0) {
                    // try to find another alive hero
                    hero = pickRandomAliveHero();
                    if (hero == null) {
                        break;
                    }
                }

                monsterAttackOp(monster, hero);

                if (!heroesAlive()) {
                    break;
                }
            }

            // ---------- Regeneration ----------
            regenerateHeroes();
            regenerateMonsters();
        }

        if (!heroesAlive()) {
            System.out.println("All heroes have been defeated. Game over!");
            return false;
        } else {
            System.out.println("All monsters have been defeated. You win!");
            rewardHeroes();
            reviveHeroes();
            return true;
        }
    }

    // ===================== STATUS & CHECKS =====================

    private boolean heroesAlive() {
        for (Hero h : heroes) {
            if (h.getHP() > 0) return true;
        }
        return false;
    }

    private boolean monstersAlive() {
        for (Monster m : monsters) {
            if (m.getHP() > 0) return true;
        }
        return false;
    }

    private void printStatus() {
        System.out.println("\n===== HEROES =====");
        for (Hero hero : heroes) {
            System.out.printf("%s - HP: %.2f/%.2f, MP: %.2f/%.2f, Level: %d, Gold: %d, EXP: %d%n",
                    hero.getName(),
                    hero.getHP(), hero.getHP_MAX(),
                    hero.getMP(), hero.getMP_MAX(),
                    hero.getLevel(),
                    hero.getTotalGold(),
                    hero.getExperiencePoints());
        }

        System.out.println("\n===== MONSTERS =====");
        for (Monster monster : monsters) {
            System.out.printf("%s - HP: %.2f, Level: %d, Damage: %.2f, Defense: %.2f, Dodge: %.2f%n",
                    monster.getName(),
                    monster.getHP(),
                    monster.getLevel(),
                    monster.getBaseDamageValue(),
                    monster.getDefenseValue(),
                    monster.getDodgeAbilityValue());
        }
    }

    private Hero pickRandomAliveHero() {
        ArrayList<Hero> alive = new ArrayList<>();
        for (Hero h : heroes) {
            if (h.getHP() > 0) alive.add(h);
        }
        if (alive.isEmpty()) return null;
        return alive.get(random.nextInt(alive.size()));
    }

    // ===================== HERO ACTION CHOICES =====================

    private int askHeroAction(Scanner sc, Hero hero) {
        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1 - Attack");
            System.out.println("2 - Cast Spell");
            System.out.println("3 - Use Potion");
            System.out.println("4 - Equip Weapon/Armor");
            System.out.println("5 - Skip");

            System.out.print("Your choice: ");
            String line = sc.nextLine().trim();
            try {
                int choice = Integer.parseInt(line);
                if (choice >= 1 && choice <= 5) {
                    return choice;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid choice. Please enter a number between 1 and 5.");
        }
    }

    private Monster chooseMonsterTarget(Scanner sc) {
        ArrayList<Monster> alive = new ArrayList<>();
        for (Monster m : monsters) {
            if (m.getHP() > 0) alive.add(m);
        }
        if (alive.isEmpty()) return null;

        System.out.println("Choose a monster to target:");
        for (int i = 0; i < alive.size(); i++) {
            Monster m = alive.get(i);
            System.out.printf("[%d] %s (HP: %.2f)%n", i, m.getName(), m.getHP());
        }

        while (true) {
            System.out.print("Your choice: ");
            String line = sc.nextLine().trim();
            try {
                int idx = Integer.parseInt(line);
                if (idx >= 0 && idx < alive.size()) {
                    return alive.get(idx);
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid choice. Please enter a valid index.");
        }
    }

    // ===================== HERO OPERATIONS =====================

    private void heroAttackOp(Hero hero, Monster monster) {
        double dodgeChance = monster.getDodgeAbilityValue() * DODGE_CONSTANT;
        double randomValue = random.nextDouble();

        if (randomValue < dodgeChance) {
            System.out.println(monster.getName() + " dodged the attack!");
            return;
        }

        double damage = hero.getStrengthValue();
        Weapon weapon = hero.getEquippedWeapon();
        if (weapon != null) {
            damage += weapon.getDamageValue();
        }

        // Apply monster defense
        double effectiveDamage = (damage - monster.getDefenseValue()) * 0.05;
        if (effectiveDamage < 0) effectiveDamage = 0;

        monster.setHP(monster.getHP() - effectiveDamage);
        System.out.printf("%s dealt %.2f damage to %s.%n",
                hero.getName(), effectiveDamage, monster.getName());
    }

    private void heroCastSpell(Scanner sc, Hero hero) {
        Inventory inventory = hero.getInventory();
        if (inventory == null) {
            System.out.println("No inventory available.");
            return;
        }

        ArrayList<Spell> iceSpells = inventory.getIceSpells();
        ArrayList<Spell> fireSpells = inventory.getFireSpells();
        ArrayList<Spell> lightningSpells = inventory.getLightningSpells();

        boolean hasAnySpell =
                (iceSpells != null && !iceSpells.isEmpty()) ||
                        (fireSpells != null && !fireSpells.isEmpty()) ||
                        (lightningSpells != null && !lightningSpells.isEmpty());

        if (!hasAnySpell) {
            System.out.println("No spells available in inventory.");
            return;
        }

        System.out.println("Choose spell type:");
        System.out.println("0 - ICE");
        System.out.println("1 - FIRE");
        System.out.println("2 - LIGHTNING");

        int typeChoice;
        while (true) {
            System.out.print("Your choice: ");
            String line = sc.nextLine().trim();
            try {
                typeChoice = Integer.parseInt(line);
                if (typeChoice < 0 || typeChoice > 2) {
                    System.out.println("Enter 0, 1, or 2.");
                    continue;
                }
                if (typeChoice == 0 && (iceSpells == null || iceSpells.isEmpty())) {
                    System.out.println("No ICE spells available. Choose another type.");
                    continue;
                }
                if (typeChoice == 1 && (fireSpells == null || fireSpells.isEmpty())) {
                    System.out.println("No FIRE spells available. Choose another type.");
                    continue;
                }
                if (typeChoice == 2 && (lightningSpells == null || lightningSpells.isEmpty())) {
                    System.out.println("No LIGHTNING spells available. Choose another type.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 0, 1, or 2.");
            }
        }

        ArrayList<Spell> chosenList;
        String spellTypeLabel;
        switch (typeChoice) {
            case 0:
                chosenList = iceSpells;
                spellTypeLabel = "ICE";
                break;
            case 1:
                chosenList = fireSpells;
                spellTypeLabel = "FIRE";
                break;
            default:
                chosenList = lightningSpells;
                spellTypeLabel = "LIGHTNING";
                break;
        }

        System.out.println("Choose a " + spellTypeLabel + " spell:");
        for (int i = 0; i < chosenList.size(); i++) {
            Spell s = chosenList.get(i);
            System.out.printf("[%d] %s | Cost: %d | Level: %d | Damage: %d | Mana Cost: %d%n",
                    i, s.getName(), s.getPrice(), s.getLevel(),
                    s.getDamageValue(), s.getManaCost());
        }

        Spell spell;
        while (true) {
            System.out.print("Your spell choice: ");
            String line = sc.nextLine().trim();
            try {
                int idx = Integer.parseInt(line);
                if (idx >= 0 && idx < chosenList.size()) {
                    spell = chosenList.get(idx);
                    break;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid choice. Please select a valid spell index.");
        }

        Monster target = chooseMonsterTarget(sc);
        if (target == null) return;

        heroSpellAttackOp(hero, target, spell);
    }

    private void heroUsePotion(Scanner sc, Hero hero) {
        Inventory inventory = hero.getInventory();
        if (inventory == null) {
            System.out.println("No inventory available.");
            return;
        }

        ArrayList<Potion> potions = inventory.getPotions();
        if (potions == null || potions.isEmpty()) {
            System.out.println("No potions available.");
            return;
        }

        System.out.println("Choose a potion:");
        for (int i = 0; i < potions.size(); i++) {
            Potion p = potions.get(i);

            // p.getAttributes() → List<AttributeType>
            java.util.List<AttributeType> attrs = p.getAttributes();
            StringBuilder attrNames = new StringBuilder();

            if (attrs != null && !attrs.isEmpty()) {
                for (int j = 0; j < attrs.size(); j++) {
                    AttributeType attr = attrs.get(j);
                    if (attr == null) continue;

                    // Use pretty names instead of raw enum if you want
                    attrNames.append(attr.name());
                    if (j < attrs.size() - 1) {
                        attrNames.append(", ");
                    }
                }
            } else {
                attrNames.append("None");
            }

            System.out.printf(
                    "[%d] %s | Cost: %d | Level: %d | Increase: %d | Affects: %s%n",
                    i,
                    p.getName(),
                    p.getPrice(),
                    p.getLevel(),
                    p.getEffectAmountValue(),
                    attrNames.toString()
            );
        }

        Potion potion;
        while (true) {
            System.out.print("Your potion choice: ");
            String line = sc.nextLine().trim();
            try {
                int idx = Integer.parseInt(line);
                if (idx >= 0 && idx < potions.size()) {
                    potion = potions.get(idx);
                    break;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Invalid choice. Please select a valid potion index.");
        }

        heroPotionOp(hero, potion);
        // Optional: remove potion after use
        // potions.remove(potion);
    }

    private void heroEquipItem(Scanner sc, Hero hero) {
        Inventory inventory = hero.getInventory();
        if (inventory == null) {
            System.out.println("No inventory available.");
            return;
        }

        boolean hasWeapons = inventory.getWeapons() != null && !inventory.getWeapons().isEmpty();
        boolean hasArmors = inventory.getArmors() != null && !inventory.getArmors().isEmpty();

        if (!hasWeapons && !hasArmors) {
            System.out.println("No weapons or armors available to equip.");
            return;
        }

        System.out.println("Equip:");
        System.out.println("0 - Weapon");
        System.out.println("1 - Armor");

        int choice;
        while (true) {
            System.out.print("Your choice: ");
            String line = sc.nextLine().trim();
            try {
                choice = Integer.parseInt(line);
                if (choice == 0 && !hasWeapons) {
                    System.out.println("No weapons available. Choose Armor or cancel.");
                    continue;
                }
                if (choice == 1 && !hasArmors) {
                    System.out.println("No armors available. Choose Weapon or cancel.");
                    continue;
                }
                if (choice == 0 || choice == 1) break;
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Please enter 0 or 1.");
        }

        if (choice == 0) {
            ArrayList<Weapon> weapons = inventory.getWeapons();
            System.out.println("Choose a weapon:");
            for (int i = 0; i < weapons.size(); i++) {
                Weapon w = weapons.get(i);
                System.out.printf("[%d] %s | Cost: %d | Level: %d | Damage: %d | Hands: %d%n",
                        i, w.getName(), w.getPrice(), w.getLevel(),
                        w.getDamageValue(), w.getRequiredHandNum());
            }

            Weapon weapon;
            while (true) {
                System.out.print("Your weapon choice: ");
                String line = sc.nextLine().trim();
                try {
                    int idx = Integer.parseInt(line);
                    if (idx >= 0 && idx < weapons.size()) {
                        weapon = weapons.get(idx);
                        break;
                    }
                } catch (NumberFormatException ignored) {
                }
                System.out.println("Invalid choice. Please select a valid weapon index.");
            }

            heroEquipWeapon(hero, weapon);
        } else {
            ArrayList<Armor> armors = inventory.getArmors();
            System.out.println("Choose an armor:");
            for (int i = 0; i < armors.size(); i++) {
                Armor a = armors.get(i);
                System.out.printf("[%d] %s | Cost: %d | Level: %d | Damage Reduction: %d%n",
                        i, a.getName(), a.getPrice(), a.getLevel(), a.getDamageReductionValue());
            }

            Armor armor;
            while (true) {
                System.out.print("Your armor choice: ");
                String line = sc.nextLine().trim();
                try {
                    int idx = Integer.parseInt(line);
                    if (idx >= 0 && idx < armors.size()) {
                        armor = armors.get(idx);
                        break;
                    }
                } catch (NumberFormatException ignored) {
                }
                System.out.println("Invalid choice. Please select a valid armor index.");
            }

            heroEquipArmor(hero, armor);
        }
    }

    // ===================== HERO EFFECTS =====================

    private void heroSpellAttackOp(Hero hero, Monster monster, Spell spell) {
        if (hero.getMP() < spell.getManaCost()) {
            System.out.println("Not enough mana to cast this spell.");
            return;
        }

        hero.setMP(hero.getMP() - spell.getManaCost());

        double dodgeChance = monster.getDodgeAbilityValue() * DODGE_CONSTANT;
        double randomValue = random.nextDouble();

        if (randomValue < dodgeChance) {
            System.out.println(monster.getName() + " dodged the spell!");
            return;
        }

        double damage = spell.getDamageValue() +
                (hero.getDexterityValue() / 10000.0) * spell.getDamageValue();

        // Apply monster defense
        double effectiveDamage = damage - monster.getDefenseValue();
        if (effectiveDamage < 0) effectiveDamage = 0;

        monster.setHP(monster.getHP() - effectiveDamage);
        System.out.printf("%s casts %s and deals %.2f damage to %s.%n",
                hero.getName(), spell.getName(), effectiveDamage, monster.getName());

        // Debuff based on spell type
        SpellType type = spell.getSpellType();
        if (type == SpellType.ICE) {
            monster.setBaseDamageValue(monster.getBaseDamageValue() * 0.9);
            System.out.println(monster.getName() + "'s damage has been reduced!");
        } else if (type == SpellType.FIRE) {
            monster.setDefenseValue(monster.getDefenseValue() * 0.9);
            System.out.println(monster.getName() + "'s defense has been reduced!");
        } else if (type == SpellType.LIGHTNING) {
            monster.setDodgeAbilityValue(monster.getDodgeAbilityValue() * 0.9);
            System.out.println(monster.getName() + "'s dodge chance has been reduced!");
        }
    }

    private void heroPotionOp(Hero hero, Potion potion) {
        int inc = potion.getEffectAmountValue();

        // getAttributes() → ArrayList<AttributeType>
        java.util.List<AttributeType> attrs = potion.getAttributes();

        if (attrs == null || attrs.isEmpty()) {
            System.out.println("This potion has no affected attributes.");
            return;
        }

        for (AttributeType attr : attrs) {
            if (attr == null) continue;

            switch (attr) {
                case HEALTH:
                    hero.setHP_MAX(hero.getHP_MAX() + inc);
                    hero.setHP(hero.getHP() + inc);
                    break;
                case MANA:
                    hero.setMP_MAX(hero.getMP_MAX() + inc);
                    hero.setMP(hero.getMP() + inc);
                    break;
                case STRENGTH:
                    hero.setStrengthValue(hero.getStrengthValue() + inc);
                    break;
                case DEXTERITY:
                    hero.setDexterityValue(hero.getDexterityValue() + inc);
                    break;
                case AGILITY:
                    hero.setAgilityValue(hero.getAgilityValue() + inc);
                    break;
                default:
                    // Unknown or unsupported attribute, ignore
                    break;
            }
        }

        System.out.printf("%s used %s and increased attributes.%n",
                hero.getName(), potion.getName());
    }

    private void heroEquipWeapon(Hero hero, Weapon weapon) {
        if (hero.getLevel() < weapon.getLevel()) {
            System.out.println("Hero level too low to equip this weapon.");
            return;
        }
        hero.setEquippedWeapon(weapon);
        System.out.printf("%s has equipped %s.%n", hero.getName(), weapon.getName());
    }

    private void heroEquipArmor(Hero hero, Armor armor) {
        if (hero.getLevel() < armor.getLevel()) {
            System.out.println("Hero level too low to equip this armor.");
            return;
        }
        hero.setEquippedArmor(armor);
        System.out.printf("%s has equipped %s.%n", hero.getName(), armor.getName());
    }

    // ===================== MONSTER OPERATIONS =====================

    private void monsterAttackOp(Monster monster, Hero hero) {
        double dodgeChance = hero.getAgilityValue() * DODGE_CONSTANT;
        double randomValue = random.nextDouble();

        if (randomValue < dodgeChance) {
            System.out.println(hero.getName() + " dodged the attack!");
            return;
        }

        double damage = (monster.getBaseDamageValue() * 0.05);
        Armor armor = hero.getEquippedArmor();
        if (armor != null) {
            damage -= armor.getDamageReductionValue();
        }
        if (damage < 0) damage = 0;

        hero.setHP(hero.getHP() - damage);
        System.out.printf("%s dealt %.2f damage to %s.%n",
                monster.getName(), damage, hero.getName());
    }

    // ===================== POST-BATTLE =====================

    private void regenerateHeroes() {
        for (Hero hero : heroes) {
            if (hero.getHP() > 0) {
                // 10% regen of missing HP/MP
                double newHP = hero.getHP() + 0.1 * hero.getHP_MAX();
                if (newHP > hero.getHP_MAX()) newHP = hero.getHP_MAX();
                hero.setHP(newHP);

                double newMP = hero.getMP() + 0.1 * hero.getMP_MAX();
                if (newMP > hero.getMP_MAX()) newMP = hero.getMP_MAX();
                hero.setMP(newMP);
            }
        }
    }

    private void regenerateMonsters() {
        for (Monster monster : monsters) {
            if (monster.getHP() > 0) {
                double maxHP = monster.getLevel() * 100;
                double newHP = monster.getHP() + 0.1 * maxHP;
                if (newHP > maxHP) newHP = maxHP;
                monster.setHP(newHP);
            }
        }
    }

    private void rewardHeroes() {
        int experienceGain = 2;
        int moneyGain = 100;

        for (Hero hero : heroes) {
            if (hero.getHP() > 0) {
                hero.setExperiencePoints(
                        hero.getExperiencePoints() + experienceGain * hero.getLevel()
                );
                hero.setTotalGold(
                        hero.getTotalGold() + moneyGain * hero.getLevel()
                );
            }
        }
    }

    private void reviveHeroes() {
        for (Hero hero : heroes) {
            if (hero.getHP() <= 0) {
                // Revive at half HP and some mana
                hero.setHP(hero.getHP_MAX() * 0.5);
                hero.setMP(hero.getMP_MAX() * 0.5);
                System.out.printf("%s has been revived with %.1f HP and %.1f MP.%n",
                        hero.getName(), hero.getHP(), hero.getMP());
            }
        }
    }
}
