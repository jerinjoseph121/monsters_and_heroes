package Common.Catalog.ItemCatalog.SpellCatalog;

import Common.utils.ItemType;
import Common.utils.SpellType;

public enum IceSpellCatalog {
    SNOW_CANNON(0, "Snow_Cannon", 500, 2, 650, 250),
    ICE_BLADE(1, "Ice_Blade", 250, 1, 450, 100),
    FROST_BLIZZARD(2, "Frost_Blizzard", 750, 5, 850, 350),
    ARCTIC_STORM(3, "Arctic_Storm", 700, 6, 800, 300);

    private final int typeId;
    private final String name;
    private final int cost;
    private final int requiredLevel;
    private final int damage;
    private final int manaCost;
    public static final int count = 4;

    IceSpellCatalog(int typeId, String name, int cost, int requiredLevel, int damage, int manaCost) {
        this.typeId = typeId;
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public int getTypeId() {
        return typeId;
    }

    public static IceSpellCatalog fromTypeId(int typeId) {
        for (IceSpellCatalog value : IceSpellCatalog.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No ice spell with type id " + typeId);
    }

    public ItemType getItemType() {
        return ItemType.SPELL;
    }

    public SpellType getSpellType() {
        return SpellType.ICE;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }
}
