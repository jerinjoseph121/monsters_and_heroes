package Common.Catalog.ItemCatalog.SpellCatalog;

import Common.utils.ItemType;
import Common.utils.SpellType;

public enum FireSpellCatalog {
    FLAME_TORNADO(0, "Flame_Tornado", 700, 4, 850, 300),
    BREATH_OF_FIRE(1, "Breath_of_Fire", 350, 1, 450, 100),
    HEAT_WAVE(2, "Heat_Wave", 450, 2, 600, 150),
    LAVA_COMET(3, "Lava_Comet", 800, 7, 1000, 550),
    HELL_STORM(4, "Hell_Storm", 600, 3, 950, 600);

    private final int typeId;
    private final String name;
    private final int cost;
    private final int requiredLevel;
    private final int damage;
    private final int manaCost;

    FireSpellCatalog(int typeId, String name, int cost, int requiredLevel, int damage, int manaCost) {
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

    public static FireSpellCatalog fromTypeId(int typeId) {
        for (FireSpellCatalog value : FireSpellCatalog.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No fire spell with type id " + typeId);
    }

    public ItemType getItemType() {
        return ItemType.SPELL;
    }

    public SpellType getSpellType() {
        return SpellType.FIRE;
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
