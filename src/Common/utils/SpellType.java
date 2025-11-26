package Common.utils;

public enum SpellType {
    ICE(0),
    FIRE(1),
    LIGHTNING(2);

    private final int typeId;

    SpellType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
