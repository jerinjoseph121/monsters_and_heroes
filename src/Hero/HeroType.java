package Hero;

public enum HeroType {
    WARRIOR(0),
    SORCERER(1),
    PALADIN(2);

    private final int typeId;

    HeroType(int typeId) {
        this.typeId = typeId;
    }

    public static HeroType fromTypeId(int typeId) {
        for (HeroType value : HeroType.values()) {
            if (value.getTypeId() == typeId) {
                return value;
            }
        }
        // Handle cases where no matching ID is found (e.g., throw an exception or return null)
        throw new IllegalArgumentException("No hero type with type id " + typeId);
    }

    public int getTypeId() {
        return typeId;
    }
}
