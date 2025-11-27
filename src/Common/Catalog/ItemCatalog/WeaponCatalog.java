package Common.Catalog.ItemCatalog;

import Common.utils.ItemType;

public enum WeaponCatalog {
    SWORD(0, "Sword", 500, 1, 800, 1),
    BOW(1, "Bow", 300, 2, 500, 2),
    SCYTHE(2, "Scythe", 1000, 6, 1100, 2),
    AXE(3, "Axe", 550, 5, 850, 1),
    T_SWORDS(4, "T Swords", 1400, 8, 1600, 2),
    DAGGER(5, "Dagger", 200, 1, 250, 1);

    private final int typeId;
    private final String name;
    private final int cost;
    private final int level;
    private final int damage;
    private final int requiredHands;
    public static final int count = 6;

    WeaponCatalog(int typeId, String name, int cost, int level, int damage, int requiredHands) {
        this.typeId = typeId;
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.damage = damage;
        this.requiredHands = requiredHands;
    }

    public int getTypeId() {
        return typeId;
    }

    public static WeaponCatalog fromTypeId(int typeId) {
        for (WeaponCatalog value : WeaponCatalog.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No weapon with type id " + typeId);
    }

    public ItemType getItemType() {
        return ItemType.WEAPON;
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

    public int getDamage() {
        return damage;
    }

    public int getRequiredHands() {
        return requiredHands;
    }
}
