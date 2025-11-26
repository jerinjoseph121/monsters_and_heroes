package Common.utils;

public enum ItemType {
    WEAPON(0),
    ARMOR(1),
    POTION(2),
    SPELL(3);

    private final int typeId;

    ItemType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
