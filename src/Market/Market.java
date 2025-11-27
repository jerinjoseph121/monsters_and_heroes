package Market;

import Common.AbstractClasses.Item;
import Common.utils.Helper;
import Hero.Hero;
import Inventory.Inventory;
import Item.Armor;
import Item.Potion;
import Item.Spell;
import Item.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Market {
    private ArrayList<Item> items;

    public Market() {
        items = Helper.getRandomItems();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    /**
     * Main market loop: choose hero, then buy/sell for that hero.
     */
    public void openMarket(Scanner sc, ArrayList<Hero> party) {
        System.out.println("\n===== MARKET =====");

        if (party == null || party.isEmpty()) {
            System.out.println("No heroes in the party. Leaving market.");
            return;
        }

        while (true) {
            System.out.println("\nChoose a hero to trade with (or 0 to leave market):");
            for (int i = 0; i < party.size(); i++) {
                Hero h = party.get(i);
                System.out.printf("[%d] %s (Level %d, Gold: %d)%n",
                        i + 1, h.getName(), h.getLevel(), h.getTotalGold());
            }

            int heroChoice = readInt(sc, "Your choice: ", 0, party.size());
            if (heroChoice == 0) {
                System.out.println("Leaving market...");
                break;
            }

            Hero currentHero = party.get(heroChoice - 1);
            heroMarketMenu(sc, currentHero);
        }
    }

    /**
     * Menu for a single hero: buy / sell / show stats / back.
     */
    private void heroMarketMenu(Scanner sc, Hero hero) {
        while (true) {
            System.out.printf("%n--- Trading with %s (Level %d, Gold: %d) ---%n",
                    hero.getName(), hero.getLevel(), hero.getTotalGold());
            System.out.println("[1] Buy items");
            System.out.println("[2] Sell items");
            System.out.println("[3] Show hero inventory");
            System.out.println("[4] Back to hero selection");

            int choice = readInt(sc, "Your choice: ", 1, 4);

            switch (choice) {
                case 1:
                    handleBuy(sc, hero);
                    break;
                case 2:
                    handleSell(sc, hero);
                    break;
                case 3:
                    Helper.showHeroInventory(hero);   // you already have this helper
                    break;
                case 4:
                    return; // back to hero selection in openMarket()
            }
        }
    }

    /**
     * Buying logic: list market items, validate level & gold, then move item to hero inventory.
     */
    private void handleBuy(Scanner sc, Hero hero) {
        if (items == null || items.isEmpty()) {
            System.out.println("The market is currently out of stock.");
            return;
        }

        while (true) {
            System.out.println("\n--- Items available to buy ---");
            for (int i = 0; i < items.size(); i++) {
                Item it = items.get(i);
                System.out.printf("[%d] %-15s | Type: %-10s | Price: %4d | Level: %d%n",
                        i + 1,
                        it.getName(),
                        it.getItemType(),
                        it.getPrice(),
                        it.getLevel());
            }
            System.out.println("[0] Back");

            int choice = readInt(sc, "Choose item to buy: ", 0, items.size());
            if (choice == 0) return;

            Item selected = items.get(choice - 1);

            // Level check
            if (hero.getLevel() < selected.getLevel()) {
                System.out.println("Hero level too low to buy this item.");
                continue;
            }

            // Money check
            if (hero.getTotalGold() < selected.getPrice()) {
                System.out.println("Not enough gold to buy this item.");
                continue;
            }

            // Pay and transfer
            hero.setTotalGold(hero.getTotalGold() - selected.getPrice());
            hero.getInventory().addItem(selected);
            removeItem(selected);

            System.out.printf("Bought %s for %d gold.%n",
                    selected.getName(), selected.getPrice());
        }
    }

    /**
     * Selling logic: collect all items from hero inventory and sell for half price.
     * Assumes Inventory has a removeItem(Item) method or similar.
     */
    private void handleSell(Scanner sc, Hero hero) {
        Inventory inventory = hero.getInventory();
        if (inventory == null) {
            System.out.println("This hero has no inventory.");
            return;
        }

        // Gather all sellable items from inventory by type
        ArrayList<Item> sellable = new ArrayList<>();
        for (Weapon w : inventory.getWeapons()) {
            sellable.add(w);
        }
        for (Armor a : inventory.getArmors()) {
            sellable.add(a);
        }
        for (Potion p : inventory.getPotions()) {
            sellable.add(p);
        }
        for (Spell s : inventory.getSpells()) {
            sellable.add(s);
        }

        if (sellable.isEmpty()) {
            System.out.println("No items to sell.");
            return;
        }

        while (true) {
            System.out.println("\n--- Items available to sell ---");
            for (int i = 0; i < sellable.size(); i++) {
                Item it = sellable.get(i);
                int sellPrice = it.getPrice() / 2;
                System.out.printf("[%d] %-15s | Type: %-10s | Sell price: %4d | Level: %d%n",
                        i + 1,
                        it.getName(),
                        it.getItemType(),
                        sellPrice,
                        it.getLevel());
            }
            System.out.println("[0] Back");

            int choice = readInt(sc, "Choose item to sell: ", 0, sellable.size());
            if (choice == 0) return;

            Item selected = sellable.get(choice - 1);
            int sellPrice = selected.getPrice() / 2;

            // Get rid of item from hero inventory
            // You may need to implement Inventory.removeItem(Item) if you don't have it yet.
            inventory.removeItem(selected);

            // Add money to hero and add item back to market stock
            hero.setTotalGold(hero.getTotalGold() + sellPrice);
            addItem(selected);

            System.out.printf("Sold %s for %d gold.%n",
                    selected.getName(), sellPrice);

            // Remove from local list too so it doesn't appear again
            sellable.remove(selected);
            if (sellable.isEmpty()) {
                System.out.println("No more items to sell.");
                return;
            }
        }
    }

    /**
     * Helper to read an int in [min, max] using nextLine() to avoid Scanner newline issues.
     */
    private int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                int val = Integer.parseInt(line);
                if (val < min || val > max) {
                    System.out.printf("Please enter a number between %d and %d.%n", min, max);
                    continue;
                }
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number.");
            }
        }
    }
}
