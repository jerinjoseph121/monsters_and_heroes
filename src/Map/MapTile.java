package Map;

import Common.AbstractClasses.Tile;
import Common.utils.Position;
import Common.utils.MapTileType;

public class MapTile extends Tile<MapTileType> {
    public MapTile(MapTileType type, Position position) {
        super.type = type;
        super.position = position;
    }

    @Override
    public MapTileType getType() {
        return type;
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
