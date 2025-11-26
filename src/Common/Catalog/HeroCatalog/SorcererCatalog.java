package Common.Catalog.HeroCatalog;

import Hero.HeroType;

public enum SorcererCatalog {
    RILLIFANE_RALLATHIL(0, "Rillifane Rallathil", 1300, 750, 450, 500, 2500, 9),
    SEGOJAN_EARTHCALLER(1, "Segojan Earthcaller", 900, 800, 500, 650, 2500, 5),
    REIGN_HAVOC(2, "Reign Havoc", 800, 800, 800, 800, 2500, 8),
    REVERIE_ASHELS(3, "Reverie Ashels", 900, 700, 650, 400, 2500, 7),
    KALABAR(4, "Kalabar", 800, 850, 400, 600, 2500, 6),
    SKYE_SOAR(5, "Skye Soar", 1000, 700, 400, 500, 2500, 5);

    private final int typeId;
    private final String name;
    private final int mana;
    private final int strength;
    private final int agility;
    private final int dexterity;
    private final int startingMoney;
    private final int startingExp;

    SorcererCatalog(int typeId, String name, int mana, int strength,
                   int agility, int dexterity, int startingMoney, int startingExp) {
        this.typeId = typeId;
        this.name = name;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.startingMoney = startingMoney;
        this.startingExp = startingExp;
    }

    public int getTypeId() {
        return typeId;
    }

    public static SorcererCatalog fromTypeId(int typeId) {
        for (SorcererCatalog value : SorcererCatalog.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No sorcerer with type id " + typeId);
    }

    public String getName() {
        return name;
    }

    public HeroType getHeroType() {
        return HeroType.SORCERER;
    }

    public int getMana() {
        return mana;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getStartingMoney() {
        return startingMoney;
    }

    public int getStartingExp() {
        return startingExp;
    }
}
