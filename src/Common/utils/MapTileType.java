package Common.utils;

public enum MapTileType {
    INACCESSIBLE(0),
    MARKET(1),
    COMMON(2);

    private final int typeId;

    MapTileType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }
}
