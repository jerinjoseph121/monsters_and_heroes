package Player;

import Common.utils.Position;
import Hero.Hero;

import java.util.ArrayList;

public class Player {
    String name;
    int heroCount;
    int heroMaxLevel;
    int totalHeroLevel;
    ArrayList<Hero> heroes;
    Position currentPosition;

    public Player(String name, int heroCount, ArrayList<Hero> heroes, Position startPosition) {
        this.name = name;
        this.heroCount = heroCount;
        this.heroes = heroes;
        totalHeroLevel = heroCount;
        heroMaxLevel = 1;
        currentPosition = startPosition;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position updatedPosition) {
        this.currentPosition = updatedPosition;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }
}
