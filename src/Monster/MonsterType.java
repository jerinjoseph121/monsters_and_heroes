package Monster;

public enum MonsterType {
    DRAGON(0),
    EXOSKELETON(1),
    SPIRIT(2);

    private final int typeId;

    MonsterType(int typeId) {
        this.typeId = typeId;
    }

    public static MonsterType fromTypeId(int typeId) {
        for (MonsterType value : MonsterType.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No monster type with type id " + typeId);
    }

    public int getTypeId() {
        return typeId;
    }
}
