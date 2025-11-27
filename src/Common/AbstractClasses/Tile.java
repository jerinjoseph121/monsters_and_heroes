package Common.AbstractClasses;

import Common.utils.Position;
import Market.Market;

public abstract class Tile <T> {
    protected T type;
    protected Position position;
    protected Market market;

    public abstract T getType();
    public abstract Position getPosition();
    public abstract Market getMarket();
}
