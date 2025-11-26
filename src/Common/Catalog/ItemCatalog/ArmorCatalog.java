package Common.Catalog.ItemCatalog;

import Common.utils.ItemType;

public enum ArmorCatalog {
    PLATINUM_SHIELD(0, "Platinum Shield", 150, 1, 200),
    BREASTPLATE(1, "Breastplate", 350, 3, 600),
    FULL_BODY_ARMOR(2, "Full Body Armor", 1000, 8, 1100),
    WIZARD_SHIELD(3, "Wizard Shield", 1200, 10, 1500),
    GUARDIAN_ANGEL(4, "Guardian Angel", 1000, 10, 1000);

    private final int typeId;
    private final String name;
    private final int cost;
    private final int level;
    private final int damageReduced;

    ArmorCatalog(int typeId, String name, int cost, int level, int damageReduced) {
        this.typeId = typeId;
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.damageReduced = damageReduced;
    }

    public int getTypeId() {
        return typeId;
    }

    public static ArmorCatalog fromTypeId(int typeId) {
        for (ArmorCatalog value : ArmorCatalog.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No armor with type id " + typeId);
    }

    public ItemType getItemType() {
        return ItemType.ARMOR;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getLevel() {
        return level;
    }

    public int getDamageReduced() {
        return damageReduced;
    }
}
