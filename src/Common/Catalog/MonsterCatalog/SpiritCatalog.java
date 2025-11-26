package Common.Catalog.MonsterCatalog;

import Monster.MonsterType;

public enum SpiritCatalog {
    CASPER(0, "Casper", 1, 100, 100, 50),
    BLINKY(1, "Blinky", 1, 450, 350, 35),
    ANDREALPHUS(2, "Andrealphus", 2, 600, 500, 40),
    ANDROMALIUS(3, "Andromalius", 3, 550, 450, 25),
    CHIANG_SHIH(4, "Chiang-shih", 4, 700, 600, 40),
    FALLEN_ANGEL(5, "Fallen Angel", 5, 800, 700, 50),
    ERESHKIGALL(6, "Ereshkigall", 6, 950, 450, 35),
    MELCHIRESAS(7, "Melchiresas", 7, 350, 150, 75),
    JORMUNNGAND(8, "Jormunngand", 8, 600, 900, 20),
    RAKKSHASASS(9, "Rakkshasass", 9, 550, 600, 35),
    TALTECUHTLI(10, "Taltecuhtli", 10, 300, 200, 50);

    private final int typeId;
    private final String name;
    private final int level;
    private final int baseDamageValue;
    private final int defenseValue;
    private final int dodgeAbilityValue;

    SpiritCatalog(int typeId, String name, int level,
                  int baseDamageValue, int defenseValue, int dodgeAbilityValue) {
        this.typeId = typeId;
        this.name = name;
        this.level = level;
        this.baseDamageValue = baseDamageValue;
        this.defenseValue = defenseValue;
        this.dodgeAbilityValue = dodgeAbilityValue;
    }

    public static SpiritCatalog fromTypeId(int typeId) {
        for (SpiritCatalog value : SpiritCatalog.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No spirit with type id " + typeId);
    }

    public static SpiritCatalog fromLevel(int level) {
        SpiritCatalog candidateSpirit = SpiritCatalog.values()[0];

        for (SpiritCatalog value : SpiritCatalog.values()) {
            if (Math.abs(value.getLevel() - level) < Math.abs(candidateSpirit.getLevel() - level)) {
                if (value.getLevel() <= level)
                    candidateSpirit = value;
            }
        }

        return candidateSpirit;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public MonsterType getMonsterType() {
        return MonsterType.SPIRIT;
    }

    public int getLevel() {
        return level;
    }

    public int getBaseDamageValue() {
        return baseDamageValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }
    public int getDodgeAbilityValue() {
        return dodgeAbilityValue;
    }

}
