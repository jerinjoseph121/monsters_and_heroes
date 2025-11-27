package Map;

import Common.AbstractClasses.Tile;
import Common.utils.Position;
import Common.utils.MapTileType;
import Market.Market;

public class MapTile extends Tile<MapTileType> {
    public MapTile(MapTileType type, Position position, Market market) {
        super.type = type;
        super.position = position;
        super.market = market;
    }

    @Override
    public MapTileType getType() {
        return type;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Market getMarket() {
        return market;
    }
}
