package io.github.superslowjelly.finalproject.entities;

public class Warrior extends Entity {

    // Base stats.
    private static final int[] BASE_STATS = {1, 2, 3};

    // Constructors.
    public Warrior(String name) { // Used to create playable characters, slightly more powerful than the base for balance.
        super("Warrior " + name, BASE_STATS[0], BASE_STATS[1], BASE_STATS[2]);
    }

    public Warrior(int level) { // Used to create enemies.
        super("Enemy Warrior", BASE_STATS[0], BASE_STATS[1], BASE_STATS[2], level);
    }

    public Warrior(String name, int level) { // Used to create bosses.
        super("Warrior " + name, BASE_STATS[0], BASE_STATS[1], BASE_STATS[2], level);
    }

    @Override
    public double getBaseStrength() {
        return BASE_STATS[1] + this.getLevel();
    }

    @Override
    public double getBaseHealth() {
        return BASE_STATS[2] + this.getLevel();
    }
}
