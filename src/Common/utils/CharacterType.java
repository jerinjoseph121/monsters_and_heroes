package Common.utils;

public enum CharacterType {
    HERO(0),
    MONSTER(1);

    private final int typeId;

    CharacterType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
