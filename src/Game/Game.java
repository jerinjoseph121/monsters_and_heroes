package Game;

import Battle.Battle;
import Common.Catalog.HeroCatalog.PaladinCatalog;
import Common.Catalog.HeroCatalog.SorcererCatalog;
import Common.Catalog.HeroCatalog.WarriorCatalog;
import Common.utils.MapTileType;
import Common.utils.Position;
import Hero.Hero;
import Hero.HeroType;
import Player.Player;
import Map.Map;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    Player player;
    MapTileType playerMapTileType;
    Map map;
    Scanner sc;
    Double battleProb;


    public Game() {
        sc = new Scanner(System.in);
        launchGame();
    }

    public void launchGame() {
        System.out.println("WELCOME TO MONSTERS AND HEROES");

        System.out.print("\nEnter your name: ");
        sc.nextLine(); // <-- clear leftover newline if needed
        String playerName = sc.nextLine().trim();

        if (playerName.isEmpty()) {
            playerName = "Player";   // default name
        }

        System.out.println("\n========= MAP CONFIGURATIONS =========");
        System.out.println("Enter the dimensions of the world map.");
        int totalRows;
        while (true) {
            System.out.print("Rows: ");
            if (sc.hasNextInt()) {
                totalRows = sc.nextInt();
                if (totalRows > 0) break;
                System.out.println("Invalid input. Rows must be a positive number.");
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // clear invalid token
            }
        }

        int totalColumns;
        while (true) {
            System.out.print("Columns: ");
            if (sc.hasNextInt()) {
                totalColumns = sc.nextInt();
                if (totalColumns > 0) break;
                System.out.println("Invalid input. Columns must be a positive number.");
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // clear invalid token
            }
        }

        System.out.println("\nNow enter certain configurations for different features in the map");
        // ===== BATTLE PROBABILITY =====
        while (true) {
            System.out.print("Probability [0-1] [Default 0.4]: ");
            sc.nextLine(); // Clear leftover newline from previous input if needed
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                battleProb = 0.4;  // default
                break;
            }

            try {
                battleProb = Double.parseDouble(input);
                if (battleProb >= 0 && battleProb <= 1) {
                    break;  // valid
                }
                System.out.println("Invalid value! Enter a number between 0 and 1.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
            }
        }

        // ===== BLOCK PROBABILITY =====
        double blockProb;
        while (true) {
            System.out.print("Block Probability [0-1] [Default 0.2]: ");
            sc.nextLine(); // Clear leftover newline from previous input if needed
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                blockProb = 0.2;   // default
                break;
            }

            try {
                blockProb = Double.parseDouble(input);
                if (blockProb >= 0 && blockProb <= 1) {
                    break;  // valid
                }
                System.out.println("Invalid value! Enter a number between 0 and 1.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
            }
        }

        // ===== MARKET PROBABILITY =====
        double marketProb;
        while (true) {
            System.out.print("Market Probability [0-1] [Default 0.3]: ");
            sc.nextLine(); // Clear leftover newline from previous input if needed
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                marketProb = 0.3;  // default
                break;
            }

            try {
                marketProb = Double.parseDouble(input);
                if (marketProb >= 0 && marketProb <= 1) {
                    break;  // valid
                }
                System.out.println("Invalid value! Enter a number between 0 and 1.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
            }
        }

        System.out.println("\n========= HERO CONFIGURATIONS =========");
        System.out.print("Select the number of heroes in your party: ");
        int heroCount = sc.nextInt();

        ArrayList<Hero> heroes = new ArrayList<>();

        for (int i = 0; i < heroCount; i++) {
            System.out.printf("%n===== HERO %d =====", i);

            // Input hero type
            System.out.printf("Enter the type of hero %d [Default WARRIOR - 0]: %n", i);
            System.out.println("WARRIOR  - 0");
            System.out.println("SORCERER - 1");
            System.out.println("PALADIN  - 2");
            int heroTypeId;
            while (true) {
                System.out.print("Your choice (0-2): ");
                sc.nextLine(); // Clear leftover newline from previous input if needed
                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    heroTypeId = 0; // default WARRIOR
                    break;
                }

                try {
                    heroTypeId = Integer.parseInt(input);
                    if (heroTypeId >= 0 && heroTypeId <= 2) {
                        break;
                    }
                    System.out.println("Invalid input! Please enter a number between 0 and 2.");
                }
                catch (NumberFormatException ex) {
                    System.out.println("Invalid input! Please enter a valid number.");
                }
            }
            HeroType heroType = HeroType.fromTypeId(heroTypeId);

            Hero hero = null;

            switch (heroTypeId) {
                case 0:
                    System.out.println("Choose a warrior type hero:");
                    for (WarriorCatalog warrior : WarriorCatalog.values()) {
                        System.out.printf("[%d] Name: %s | Mana: %d | Strength: %d | Agility: %d | Dexterity: %d | Starting Money: %d | Starting Experience: %d\n",
                                warrior.getTypeId(), warrior.getName(), warrior.getMana(),
                                warrior.getStrength(), warrior.getAgility(), warrior.getDexterity(),
                                warrior.getStartingMoney(), warrior.getStartingExp());
                    }
                    int warriorTypeId;
                    while (true) {
                        System.out.print("Your choice: ");
                        String input = sc.nextLine().trim();

                        if (input.isEmpty()) {
                            System.out.println("Missing input! Please enter a number to choose a hero");
                            continue;
                        }

                        try {
                            warriorTypeId = Integer.parseInt(input);
                            if (warriorTypeId >= 0 && warriorTypeId < WarriorCatalog.values().length) {
                                break;
                            }
                            System.out.printf("Invalid input! Please enter a number between 0 and %d.\n", WarriorCatalog.values().length - 1);
                        }
                        catch (NumberFormatException ex) {
                            System.out.println("Invalid input! Please enter a valid number.");
                        }
                    }

                    WarriorCatalog chosenWarrior = WarriorCatalog.fromTypeId(warriorTypeId);
                    hero = new Hero(chosenWarrior.getName(), chosenWarrior.getHeroType(), chosenWarrior.getMana(),
                            chosenWarrior.getStrength(), chosenWarrior.getDexterity(), chosenWarrior.getAgility(),
                            chosenWarrior.getStartingMoney(), chosenWarrior.getStartingExp());
                    break;
                case 1:
                    System.out.println("Choose a sorcerer type hero:");
                    for (SorcererCatalog sorcerer : SorcererCatalog.values()) {
                        System.out.printf("[%d] Name: %s | Mana: %d | Strength: %d | Agility: %d | Dexterity: %d | Starting Money: %d | Starting Experience: %d \n",
                                sorcerer.getTypeId(), sorcerer.getName(), sorcerer.getMana(),
                                sorcerer.getStrength(), sorcerer.getAgility(), sorcerer.getDexterity(),
                                sorcerer.getStartingMoney(), sorcerer.getStartingExp());
                    }
                    int sorcererTypeId;
                    while (true) {
                        System.out.print("Your choice: ");
                        String input = sc.nextLine().trim();

                        if (input.isEmpty()) {
                            System.out.println("Missing input! Please enter a number to choose a hero");
                            continue;
                        }

                        try {
                            sorcererTypeId = Integer.parseInt(input);
                            if (sorcererTypeId >= 0 && sorcererTypeId < SorcererCatalog.values().length) {
                                break;
                            }
                            System.out.printf("Invalid input! Please enter a number between 0 and %d.\n", SorcererCatalog.values().length - 1);
                        }
                        catch (NumberFormatException ex) {
                            System.out.println("Invalid input! Please enter a valid number.");
                        }
                    }

                    SorcererCatalog chosenSorcerer = SorcererCatalog.fromTypeId(sorcererTypeId);
                    hero = new Hero(chosenSorcerer.getName(), chosenSorcerer.getHeroType(), chosenSorcerer.getMana(),
                            chosenSorcerer.getStrength(), chosenSorcerer.getDexterity(), chosenSorcerer.getAgility(),
                            chosenSorcerer.getStartingMoney(), chosenSorcerer.getStartingExp());
                    break;
                case 2:
                    System.out.println("Choose a paladin type hero:");
                    for (PaladinCatalog paladin : PaladinCatalog.values()) {
                        System.out.printf("[%d] Name: %s | Mana: %d | Strength: %d | Agility: %d | Dexterity: %d | Starting Money: %d | Starting Experience: %d \n",
                                paladin.getTypeId(), paladin.getName(), paladin.getMana(),
                                paladin.getStrength(), paladin.getAgility(), paladin.getDexterity(),
                                paladin.getStartingMoney(), paladin.getStartingExp());
                    }
                    int paladinTypeId;
                    while (true) {
                        System.out.print("Your choice: ");
                        String input = sc.nextLine().trim();

                        if (input.isEmpty()) {
                            System.out.println("Missing input! Please enter a number to choose a hero");
                            continue;
                        }

                        try {
                            paladinTypeId = Integer.parseInt(input);
                            if (paladinTypeId >= 0 && paladinTypeId < PaladinCatalog.values().length) {
                                break;
                            }
                            System.out.printf("Invalid input! Please enter a number between 0 and %d.\n", PaladinCatalog.values().length - 1);
                        }
                        catch (NumberFormatException ex) {
                            System.out.println("Invalid input! Please enter a valid number.");
                        }
                    }

                    PaladinCatalog chosenPaladin = PaladinCatalog.fromTypeId(paladinTypeId);
                    hero = new Hero(chosenPaladin.getName(), chosenPaladin.getHeroType(), chosenPaladin.getMana(),
                            chosenPaladin.getStrength(), chosenPaladin.getDexterity(), chosenPaladin.getAgility(),
                            chosenPaladin.getStartingMoney(), chosenPaladin.getStartingExp());
                    break;
            }

            heroes.add(hero);
        }

        System.out.println("\nNow finally enter the initial position of the player:");
        // ==== Read Start Row ====
        int startRow;
        int defaultRow = totalRows/2;
        while (true) {
            System.out.printf("Start row [0-%d] [Default is %d]: ", totalRows - 1, defaultRow);

            String input = sc.nextLine().trim();
            if (input.isEmpty()) {   // user presses enter → use default
                startRow = defaultRow;
                break;
            }

            try {
                startRow = Integer.parseInt(input);
                if (startRow >= 0 && startRow < totalRows) break;
                System.out.println("Invalid row. Please enter a value between 0 and " + (totalRows - 1));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        // ==== Read Start Col ====
        int startCol;
        int defaultCol = totalColumns/2;
        while (true) {
            System.out.printf("Start col [0-%d] [Default is %d]: ", totalColumns - 1, defaultCol);

            String input = sc.nextLine().trim();
            if (input.isEmpty()) {   // user presses enter → use default
                startCol = defaultCol;
                break;
            }

            try {
                startCol = Integer.parseInt(input);
                if (startCol >= 0 && startCol < totalColumns) break;
                System.out.println("Invalid column. Please enter a value between 0 and " + (totalColumns - 1));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        System.out.println();

        Position playerStartPosition = new Position(startRow, startCol);

        player = new Player(playerName, heroCount, heroes, playerStartPosition);

        map = new Map(totalRows, totalColumns, blockProb, marketProb, playerStartPosition);

        playerMapTileType = map.getPlayerMapTileType();

        gameStep();
    }

    // The step after the configuration is done
    private void gameStep() {
        System.out.println("=========GAMES STARTED=========");

        String choice;
        while (true) {
            map.displayMap();

            System.out.println("Enter a choice:");
            System.out.println("Move [w -> up | a -> left | s -> down | d -> right]: ");
            if (playerMapTileType.equals(MapTileType.MARKET))
                System.out.println("Enter Market [M]: ");
            System.out.println("Show inventory [I]: ");
            System.out.println("Show player stats [P]: ");
            System.out.println("Quit [Q]: ");

            System.out.print("Your choice: ");
            choice = sc.nextLine().trim();

            if (choice.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid option.\n");
                continue;
            }

            choice = choice.toUpperCase();

            // Check allowed inputs
            if (!(choice.equals("W") || choice.equals("A") || choice.equals("S") || choice.equals("D") ||
                    choice.equals("I") || choice.equals("P") || choice.equals("Q") || choice.equals("M"))) {
                System.out.println("Invalid input. Please enter a valid option.\n");
                continue;
            }

            if ((!playerMapTileType.equals(MapTileType.MARKET)) && choice.equals("M")) {
                System.out.println("Invalid input. Player cannot enter a market at this location");
                continue;
            }

            int currentRow = player.getCurrentPosition().getRow();
            int currentCol = player.getCurrentPosition().getCol();

            Position newPosition = new Position(
                    player.getCurrentPosition().getRow(),
                    player.getCurrentPosition().getCol()
            );
            boolean validMove = true;
            boolean quitGame = false;

            switch (choice) {
                case "W":
                    newPosition.setRow(currentRow - 1);
                    newPosition.setCol(currentCol);
                    validMove = movePlayer(newPosition);
                    break;
                case "A":
                    newPosition.setRow(currentRow);
                    newPosition.setCol(currentCol - 1);
                    validMove = movePlayer(newPosition);
                    break;
                case "S":
                    newPosition.setRow(player.getCurrentPosition().getRow() + 1);
                    newPosition.setCol(player.getCurrentPosition().getCol());
                    validMove = movePlayer(newPosition);
                    break;
                case "D":
                    newPosition.setRow(player.getCurrentPosition().getRow());
                    newPosition.setCol(player.getCurrentPosition().getCol() + 1);
                    validMove = movePlayer(newPosition);
                    break;
                case "I":
                    System.out.println("Inventory Showed!!");
                    break;
                case "P":
                    System.out.println("Player Stats Showed!!");
                    break;
                case "Q":
                    quitGame = true;
                    break;
                case "M":
                    System.out.println("Market Entered!!");
            }

            if (quitGame) {
                System.out.println("Game Exited!!");
                break;
            }

            if (!validMove) {
                continue;
            }
        }
    }

    private boolean movePlayer(Position updatedPlayerPosition) {
        if (!map.updatePlayerCurrentPosition(updatedPlayerPosition)) {
            System.out.println("Invalid movement. Please enter a valid movement.\n");
            return false;
        }

        player.setCurrentPosition(new Position(updatedPlayerPosition.getRow(), updatedPlayerPosition.getCol()));
        playerMapTileType = map.getPlayerMapTileType();

        if (playerMapTileType.equals(MapTileType.COMMON)) {
            if(Battle.isBattleTriggered(battleProb)) {
                Battle battle = new Battle(player.getHeroes());
                boolean isPlayerWin = battle.arenaFight();
            };
        }


        return true;
    }


}
