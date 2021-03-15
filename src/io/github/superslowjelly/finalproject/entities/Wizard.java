package io.github.superslowjelly.finalproject.entities;

public class Wizard extends Entity {

    // Base stats.
    private static final int[] BASE_STATS = {2, 3, 1};

    // Constructors.
    public Wizard(String name) { // Used to create playable characters, slightly more powerful than the base for balance.
        super("Wizard " + name, BASE_STATS[0], BASE_STATS[1], BASE_STATS[2]);
    }

    public Wizard(int level) { // Used to create enemies.
        super("Enemy Wizard", BASE_STATS[0], BASE_STATS[1], BASE_STATS[2], level);
    }

    public Wizard(String name, int level) { // Used to create bosses.
        super("Wizard " + name, BASE_STATS[0], BASE_STATS[1], BASE_STATS[2], level);
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
