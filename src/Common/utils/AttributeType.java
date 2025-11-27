package Common.utils;

public enum AttributeType {
    HEALTH(0),
    MANA(1),
    STRENGTH(2),
    AGILITY(3),
    DEXTERITY(4),
    DEFENSE(5);

    private final int typeId;

    AttributeType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
