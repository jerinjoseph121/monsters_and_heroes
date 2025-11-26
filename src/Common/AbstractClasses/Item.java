package Common.AbstractClasses;

import Common.utils.ItemType;

public abstract class Item {
    protected int id;
    protected ItemType itemType;
    protected String name;
    protected int price;
    protected int level;

    public abstract int getId();
    public abstract ItemType getItemType();
    public abstract String getName();
    public abstract int getPrice();
    public abstract int getLevel();
}
