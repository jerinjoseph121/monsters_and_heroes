package Common.Catalog.ItemCatalog;

import Common.utils.AttributeType;
import Common.utils.ItemType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PotionCatalog {
    HEALING_POTION(0, "Healing_Potion", 250, 1, 100,
            Collections.singletonList(AttributeType.HEALTH)),
    STRENGTH_POTION(1, "Strength_Potion", 200, 1, 75,
            Collections.singletonList(AttributeType.STRENGTH)),
    MAGIC_POTION(2, "Magic_Potion", 350, 2, 100,
            Collections.singletonList(AttributeType.MANA)),
    LUCK_ELIXIR(3, "Luck_Elixir", 500, 4, 65,
            Collections.singletonList(AttributeType.AGILITY)),
    MERMAID_TEARS(4, "Mermaid_Tears", 850, 5, 100,
            Arrays.asList(AttributeType.HEALTH, AttributeType.MANA, AttributeType.STRENGTH, AttributeType.AGILITY)),
    AMBROSIA(5, "Ambrosia", 1000, 8, 150,
            Arrays.asList(AttributeType.HEALTH, AttributeType.MANA, AttributeType.STRENGTH, AttributeType.DEXTERITY,
                    AttributeType.DEFENCE, AttributeType.AGILITY));


    private final int typeId;
    private final String name;
    private final int cost;
    private final int level;
    private final int attributeIncrease;
    private final List<AttributeType> attributes;


    PotionCatalog(int typeId, String name, int cost, int level, int attributeIncrease, List<AttributeType> attributes) {
        this.typeId = typeId;
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.attributeIncrease = attributeIncrease;
        this.attributes = attributes;
    }

    public int getTypeId() {
        return typeId;
    }

    public static PotionCatalog fromTypeId(int typeId) {
        for (PotionCatalog value : PotionCatalog.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No potion with type id " + typeId);
    }

    public ItemType getItemType() {
        return ItemType.POTION;
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

    public int getAttributeIncrease() {
        return attributeIncrease;
    }

    public List<AttributeType> getAttributes() {
        return attributes;
    }
}
