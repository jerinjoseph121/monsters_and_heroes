package Common.Catalog.MonsterCatalog;

import Monster.MonsterType;

public enum ExoskeletonCatalog {
    BIG_BAD_WOLF(0, "Big Bad Wolf", 1, 150, 250, 15),
    WICKED_WITCH(1, "Wicked Witch", 2, 250, 350, 25),
    BRANDOBARIS(2, "Brandobaris", 3, 350, 450, 30),
    AASTERINIAN(3, "Aasterinian", 4, 400, 500, 45),
    ST_SHARGAAS(4, "St Shargaas", 5, 550, 650, 55),
    CHRONEPSISH(5, "Chronepsish", 6, 650, 750, 60),
    DOC_OCK(6, "Doc Ock", 6, 600, 600, 55),
    CYRROLLALEE(7, "Cyrrollalee", 7, 700, 800, 75),
    KIARANSALEE(8, "Kiaransalee", 8, 850, 950, 85),
    ST_YEENOGHU(9, "St Yeenoghu", 9, 950, 850, 90),
    MERRSHAULLK(10, "Merrshaullk", 10, 1000, 900, 55),
    EXODIA(11, "Exodia", 10, 1000, 1000, 50);

    private final int typeId;
    private final String name;
    private final int level;
    private final int baseDamageValue;
    private final int defenseValue;
    private final int dodgeAbilityValue;

    ExoskeletonCatalog(int typeId, String name, int level,
                  int baseDamageValue, int defenseValue, int dodgeAbilityValue) {
        this.typeId = typeId;
        this.name = name;
        this.level = level;
        this.baseDamageValue = baseDamageValue;
        this.defenseValue = defenseValue;
        this.dodgeAbilityValue = dodgeAbilityValue;
    }

    public static ExoskeletonCatalog fromTypeId(int typeId) {
        for (ExoskeletonCatalog value : ExoskeletonCatalog.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No exoskeleton with type id " + typeId);
    }

    public static ExoskeletonCatalog fromLevel(int level) {
        ExoskeletonCatalog candidateExoskeleton = ExoskeletonCatalog.values()[0];

        for (ExoskeletonCatalog value : ExoskeletonCatalog.values()) {
            if (Math.abs(value.getLevel() - level) < Math.abs(candidateExoskeleton.getLevel() - level)) {
                if (value.getLevel() <= level)
                    candidateExoskeleton = value;
            }
        }

        return candidateExoskeleton;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public MonsterType getMonsterType() {
        return MonsterType.EXOSKELETON;
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
