package Common.Catalog.MonsterCatalog;

import Monster.MonsterType;

public enum DragonCatalog {
    NATSUNOMERYU(0, "Natsunomeryu", 1, 100, 200, 10),
    CHRYSOPHYLAX(1, "Chrysophylax", 2, 200, 500, 20),
    DESGHIDORRAH(2, "Desghidorrah", 3, 300, 400, 35),
    BUNSEN_BURNER(3, "Bunsen Burner", 4, 400, 500, 45),
    KAS_ETHELINH(4, "Kas Ethelinh", 5, 600, 500, 60),
    IGNEEL(5, "Igneel", 6, 600, 400, 60),
    PHAARTHURNAX(6, "Phaarthurnax", 6, 600, 700, 60),
    THE_SCALELESS(7, "The Scaleless", 7, 700, 600, 75),
    THE_WEATHERBE(8, "The Weatherbe", 8, 800, 900, 80),
    D_MALEFICENT(9, "D-Maleficent", 9, 900, 950, 85),
    ALEXSTRASZAN(10, "Alexstraszan", 10, 1000, 9000, 55),
    BLUE_EYES_WHITE_DRAGON(11, "Blue Eyes White Dragon", 9, 900, 600, 75);

    private final int typeId;
    private final String name;
    private final int level;
    private final int baseDamageValue;
    private final int defenseValue;
    private final int dodgeAbilityValue;

    DragonCatalog(int typeId, String name, int level,
                  int baseDamageValue, int defenseValue, int dodgeAbilityValue) {
        this.typeId = typeId;
        this.name = name;
        this.level = level;
        this.baseDamageValue = baseDamageValue;
        this.defenseValue = defenseValue;
        this.dodgeAbilityValue = dodgeAbilityValue;
    }

    public static DragonCatalog fromTypeId(int typeId) {
        for (DragonCatalog value : DragonCatalog.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No dragon with type id " + typeId);
    }

    public static DragonCatalog fromLevel(int level) {
        DragonCatalog candidateDragon = DragonCatalog.values()[0];

        for (DragonCatalog value : DragonCatalog.values()) {
            if (Math.abs(value.getLevel() - level) < Math.abs(candidateDragon.getLevel() - level)) {
                if (value.getLevel() <= level)
                    candidateDragon = value;
            }
        }

        return candidateDragon;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public MonsterType getMonsterType() {
        return MonsterType.DRAGON;
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
