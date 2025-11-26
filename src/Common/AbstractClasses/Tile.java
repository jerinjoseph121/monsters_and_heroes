package Common.AbstractClasses;

import Common.utils.Position;

public abstract class Tile <T> {
    protected T type;
    protected Position position;

    public abstract T getType();
    public abstract Position getPosition();
}
