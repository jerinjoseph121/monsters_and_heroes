package Common.Catalog.HeroCatalog;

import Common.Catalog.MonsterCatalog.DragonCatalog;
import Hero.HeroType;

public enum PaladinCatalog {
    PARZIVAL(0, "Parzival", 300, 750, 650, 700, 2500, 7),
    SEHANINE_MOONBOW(1, "Sehanine Moonbow", 300, 750, 700, 700, 2500, 7),
    SKORAEUS_STONEBONES(2, "Skoraeus Stonebones", 250, 650, 600, 350, 2500, 4),
    GARL_GLITTERGOLD(3, "Garl Glittergold", 100, 600, 500, 400, 2500, 5),
    AMARYLLIS_ASTRA(4, "Amaryllis Astra", 500, 500, 500, 500, 2500, 5),
    CALIBER_HEIST(5, "Caliber Heist", 400, 400, 400, 400, 2500, 8);

    private final int typeId;
    private final String name;
    private final int mana;
    private final int strength;
    private final int agility;
    private final int dexterity;
    private final int startingMoney;
    private final int startingExp;

    PaladinCatalog(int typeId, String name, int mana, int strength,
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

    public static PaladinCatalog fromTypeId(int typeId) {
        for (PaladinCatalog value : PaladinCatalog.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No paladin with type id " + typeId);
    }

    public String getName() {
        return name;
    }

    public HeroType getHeroType() {
        return HeroType.PALADIN;
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
