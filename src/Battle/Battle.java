package Battle;

import Common.Catalog.MonsterCatalog.DragonCatalog;
import Common.Catalog.MonsterCatalog.ExoskeletonCatalog;
import Hero.Hero;
import Inventory.Inventory;
import Item.Weapon;
import Monster.Monster;
import Monster.MonsterType;

import java.util.*;

public class Battle {
    ArrayList<Hero> heroes;
    ArrayList<Monster> monsters;
    Map<Hero, Monster> rivals;
    Scanner sc = new Scanner(System.in);

    public Battle(ArrayList<Hero> heroes) {
        this.heroes = heroes;
        this.monsters = new ArrayList<Monster>();
        this.rivals = new HashMap<Hero, Monster>();

        for (Hero hero : heroes) {
            int heroLevel = hero.getLevel();
            Monster monster = createMonster(hero);

            monsters.add(monster);
            rivals.put(hero, monster);
        }
    }

    public static boolean isBattleTriggered(double battleProb) {
        Random rand = new Random();

        return rand.nextDouble() < battleProb;
    }

    private Monster createMonster(Hero hero) {
        int heroLevel = hero.getLevel();
        Random rand = new Random();

        MonsterType monsterType = MonsterType.fromTypeId(rand.nextInt(3));

        Monster monster;

        if (monsterType.equals(MonsterType.DRAGON)) {
            DragonCatalog selectedDragon = DragonCatalog.fromLevel(heroLevel);
            monster = new Monster(selectedDragon.getName(), selectedDragon.getLevel(), selectedDragon.getMonsterType(),
                    selectedDragon.getBaseDamageValue(),  selectedDragon.getDefenseValue(),
                    selectedDragon.getDodgeAbilityValue());
        } else if (monsterType.equals(MonsterType.EXOSKELETON)) {
            ExoskeletonCatalog selectedExoskeleton = ExoskeletonCatalog.fromLevel(heroLevel);
            monster = new Monster(selectedExoskeleton.getName(), selectedExoskeleton.getLevel(), selectedExoskeleton.getMonsterType(),
                    selectedExoskeleton.getBaseDamageValue(),  selectedExoskeleton.getDefenseValue(),
                    selectedExoskeleton.getDodgeAbilityValue());
        } else {
            ExoskeletonCatalog selectedExoskeleton = ExoskeletonCatalog.fromLevel(heroLevel);
            monster = new Monster(selectedExoskeleton.getName(), selectedExoskeleton.getLevel(), selectedExoskeleton.getMonsterType(),
                    selectedExoskeleton.getBaseDamageValue(),  selectedExoskeleton.getDefenseValue(),
                    selectedExoskeleton.getDodgeAbilityValue());
        }

        return monster;
    };

    public boolean arenaFight() {
        System.out.println("\n===== ENTERING BATTLE =====");

        int heroCount = rivals.size();
        int monsterCount = rivals.size();

        boolean didPlayerWin = true;

        // A iteration of this loop is a round of fight with all heroes
        while (true) {
            if (heroCount == 0) {
                didPlayerWin = false;
                break;
            }
            else if (monsterCount == 0) {
                break;
            }

            int heroNumber = 1;

            // An iteration is a fight between a hero and a monster
            for (Map.Entry<Hero, Monster> rival : rivals.entrySet()) {
                Hero hero = rival.getKey();
                Monster monster = rival.getValue();

                if (hero.getHP() <= 0) {
                    heroNumber++;
                    continue;
                }

                System.out.printf("\nHERO %d: %s\n", heroNumber, hero.getName());

                int choice = -1;

                while (true) {
                    System.out.println("Enter a valid move:");
                    System.out.println("Attack [1]:");
                    System.out.println("Cast spell from inventory [2]:");
                    System.out.println("Use potion from inventory [3]:");
                    System.out.println("Equip weapon or armor [4]:");
                    System.out.print("Your choice: ");

                    // Check if input is an integer
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid input! Please enter a number between 1 and 4.\n");
                        sc.next(); // clear invalid token
                        continue;
                    }

                    choice = sc.nextInt();

                    if (choice >= 1 && choice <= 4) {
                        break;  // valid choice → exit loop
                    } else {
                        System.out.println("Invalid option! Please try again.\n");
                    }
                }

                switch(choice) {
                    case 1:
                        monster.setHP(heroAttackOp(hero, monster).getHP());
                        break;
                    case 2:
                        Inventory inventory = hero.getInventory();
                        System.out.println("Select a spell from inventory: ");
                        break;
                    case 3:
                        System.out.println("Hero use potion");
                        break;
                    case 4:
                        System.out.println("Hero use weapon");
                        break;
                }

                if (monster.getHP() <= 0) {
                    System.out.printf("%s fainted\n ", monster.getName());
                    monsterCount--;
                }
                else {
                    hero.setHP(monsterAttackOp(hero, monster).getHP());
                    System.out.println("Monster Attacks");
                }

                if (hero.getHP() <= 0) {
                    System.out.printf("%s fainted\n ", hero.getName());
                    heroCount--;
                }

                heroNumber++;
            }
        }
        
        return didPlayerWin;
    }

    // Function where hero attacks monster and returns updated monster value
    private Monster heroAttackOp(Hero hero, Monster monster) {
        Weapon equippedWeapon = hero.getEquippedWeapon();

        int heroStrength = hero.getStrengthValue();
        double heroAttackDamage = (heroStrength + equippedWeapon.getDamageValue()) * 0.05;

        double monsterDodgeChance = monster.getDodgeAbilityValue() * 0.01;

        Random rand = new Random();

        if (rand.nextDouble() <= monsterDodgeChance) {
            System.out.printf("%s dodged the attack.\n", monster.getName());
            return monster;
        }

        System.out.printf("%s deals a damage of %.2f to %s\n", hero.getName(), heroAttackDamage, monster.getName());

        double updatedMonsterHP = Math.max(0, monster.getHP() - heroAttackDamage);

        monster.setHP(updatedMonsterHP);

        return monster;
    }

    // Function where monster attacks hero and returns updated hero value
    private Hero monsterAttackOp(Hero hero, Monster monster) {
        double monsterDamage = monster.getBaseDamageValue() * 0.05;
        double heroDodgeChance = hero.getAgilityValue() * 0.002;

        Random rand = new Random();
        if (rand.nextDouble() <= heroDodgeChance) {
            System.out.printf("%s dodged the attack.\n", hero.getName());
            return hero;
        }

        System.out.printf("%s deals a damage of %.2f to %s\n", monster.getName(), monsterDamage, hero.getName());

        double updatedHeroHp = Math.max(0, hero.getHP() - monsterDamage);

        hero.setHP(updatedHeroHp);

        return hero;
    }
}
