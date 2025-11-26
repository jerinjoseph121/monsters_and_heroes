package Map;

import Common.utils.MapTileType;
import Common.utils.Position;
import Player.Player;

public class World {
    Map map;
    Player player;

    public World(Map map, Player player) {
        this.map = map;
        this.player = player;
    }

    public void displayMap() {
        map.displayMap();
    }

    public boolean updatePlayerCurrentPosition(Position updatedPlayerPosition) {
        boolean isUpdateSuccessful = map.updatePlayerCurrentPosition(updatedPlayerPosition);
        if (isUpdateSuccessful) {
            player.setCurrentPosition(updatedPlayerPosition);
        }
        return isUpdateSuccessful;
    }

    public MapTileType getPlayerMapTileType() {
        return map.getPlayerMapTileType();
    }
}
