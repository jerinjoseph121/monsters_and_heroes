package Common.Catalog.HeroCatalog;

import Hero.HeroType;

public enum WarriorCatalog {
    GAERDAL_IRONHAND(0, "Gaerdal Ironhand", 100, 700, 500, 600, 1354, 7),
    SEHANINE_MONNBOW(1, "Sehanine Monnbow", 600, 700, 800, 500, 2500, 8),
    MUAMMAN_DUATHALL(2, "Muamman Duathall", 300, 900, 500, 750, 2546, 6),
    FLANDAL_STEELSKIN(3, "Flandal Steelskin", 200, 750, 650, 700, 2500, 7),
    UNDEFEATED_YOJ(4, "Undefeated Yoj", 400, 800, 400, 700, 2500, 7),
    EUNOIA_CYN(5, "Eunoia Cyn", 400, 700, 800, 600, 2500, 6);

    private final int typeId;
    private final String name;
    private final int mana;
    private final int strength;
    private final int agility;
    private final int dexterity;
    private final int startingMoney;
    private final int startingExp;

    WarriorCatalog(int typeId, String name, int mana, int strength,
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

    public static WarriorCatalog fromTypeId(int typeId) {
        for (WarriorCatalog value : WarriorCatalog.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No warrior with type id " + typeId);
    }

    public String getName() {
        return name;
    }

    public HeroType getHeroType() {
        return HeroType.WARRIOR;
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
