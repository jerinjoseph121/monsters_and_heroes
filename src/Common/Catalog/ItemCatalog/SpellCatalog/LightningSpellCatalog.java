package Common.Catalog.ItemCatalog.SpellCatalog;

import Common.utils.ItemType;
import Common.utils.SpellType;

public enum LightningSpellCatalog {
    LIGHTNING_DAGGER(0, "Lightning Dagger", 400, 1, 500, 150),
    THUNDER_BLAST(1, "Thunder Blast", 750, 4, 950, 400),
    ELECTRIC_ARROWS(2, "Electric Arrows", 550, 5, 650, 200),
    SPARK_NEEDLES(3, "Spark Needles", 500, 2, 600, 200);

    private final int typeId;
    private final String name;
    private final int cost;
    private final int requiredLevel;
    private final int damage;
    private final int manaCost;
    public static final int count = 4;

    LightningSpellCatalog(int typeId, String name, int cost, int requiredLevel, int damage, int manaCost) {
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

    public static LightningSpellCatalog fromTypeId(int typeId) {
        for (LightningSpellCatalog value : LightningSpellCatalog.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No lightning spell with type id " + typeId);
    }

    public ItemType getItemType() {
        return ItemType.SPELL;
    }

    public SpellType getSpellType() {
        return SpellType.LIGHTNING;
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
