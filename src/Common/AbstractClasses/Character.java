package Common.AbstractClasses;

import Common.utils.CharacterType;

public abstract class Character {
    protected int id;
    protected CharacterType characterType;
    protected String name;
    protected int level;
    protected double HP;

    public abstract int getId();
    public abstract CharacterType getType();
    public abstract String getName();
    public abstract int getLevel();
    public abstract double getHP();
    public abstract void setHP(double HP);
}
