package io.github.superslowjelly.finalproject.entities;

public class Archer extends Entity {

    // Base stats.
    private static final int[] BASE_STATS = {3, 1, 2};

    // Constructors.
    public Archer(String name) { // Used to create playable characters, slightly more powerful than the base for balance.
        super("Archer " + name, BASE_STATS[0], BASE_STATS[1], BASE_STATS[2]);
    }

    public Archer(int level) { // Used to create enemies.
        super("Enemy Archer", BASE_STATS[0], BASE_STATS[1], BASE_STATS[2], level);
    }

    public Archer(String name, int level) { // Used to create bosses.
        super("Archer " + name, BASE_STATS[0], BASE_STATS[1], BASE_STATS[2], level);
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