package io.github.superslowjelly.finalproject.entities;

import io.github.superslowjelly.finalproject.utilities.Input;
import io.github.superslowjelly.finalproject.utilities.Utils;

import java.util.Random;

public abstract class Entity {

    // Constructors.
    public Entity(String name, int speed, int strength, int health) { // Used for players.
        this.status = Status.LIVING;
        this.name = name;
        this.level = 2;
        this.speed = speed + level;
        this.strength = strength + level;
        this.health = health + level;
        this.killedBoss = false;
    }

    public Entity(String name, int speed, int strength, int health, int level) { // Used for enemies.
        this.status = Status.LIVING;
        this.name = name;
        this.level = level;
        this.speed = speed + level;
        this.strength = strength + level;
        this.health = health + level;
        this.killedBoss = false;
    }

    // Status.
    private Status status;

    public boolean isDead() {
        return this.status == Status.DEAD;
    }

    public void die() {
        this.status = Status.DEAD;
    }

    public void revive() {
        this.status = Status.LIVING;
    }

    // Attack.
    public void fight(Entity target) {
        Utils.clearScreen();
        Random random = new Random();
        Utils.outputln("&0- &4FIGHT " + (this.fights + 1) + "!");
        Utils.outputln(this.toString());
        Utils.outputln("&0- &4VS");
        Utils.outputln(target.toString());
        Utils.outputln("&0- &4FIGHT!");
        while (!target.isDead() && !this.isDead()) {
            double damage;
            if (this.speed >= target.speed) { // Player is faster than target.
                if (!Input.getDecision("\n&0- &fWould you like to charge your attack to gain a damage buff? (Y/N)&0: ")) { // Player does not buff.
                    damage = Utils.roundToTwoPlaces(this.strength * (0.5 + random.nextDouble()));
                    target.deductHealth(damage);
                    Utils.outputln("\n&0- &f" + this.name + " attacked " + target.name + ", damaging them for &4[DMG] " + damage + "&f, reducing their health to &a[HP] " + (target.isDead() ? 0 : Utils.roundToTwoPlaces(target.health)) + "/" + target.getBaseHealth());
                } else { // Player buffs.
                    this.buffStrength(this.strength / 2);
                    Utils.outputln("\n&0- &f" + this.name + " abstained and gained &4[STR] " + (this.strength / 2));
                }
                if (!target.isDead()) { // Target survived attack.
                    damage = Utils.roundToTwoPlaces(target.strength * (0.5 + random.nextDouble()));
                    this.deductHealth(damage);
                    Utils.outputln("\n&0- &f" + target.name + " attacked " + this.name + ", damaging them for &4[DMG] " + damage + "&f, reducing their health to &a[HP] " + (this.isDead() ? 0 : Utils.roundToTwoPlaces(this.health)) + "/" + this.getBaseHealth());
                    if (this.isDead()) { // Target's attack killed player.
                        this.health = getBaseHealth();
                        this.strength = getBaseStrength();
                        Utils.outputln("\n&0- &f" + target.name + " killed " + this.name + "!");
                        incLosses();
                    }
                } else { // Player's attack killed target.
                    this.health = getBaseHealth();
                    this.strength = getBaseStrength();
                    Utils.outputln("\n&0- &f" + this.name + " killed " + target.name + "! " + incExperience(target.level));
                    incWins();
                }
            } else { // Target is faster than player.
                damage = Utils.roundToTwoPlaces(target.strength * (0.5 + random.nextDouble()));
                this.deductHealth(damage);
                Utils.outputln("\n&0- &f" + target.name + " attacked " + this.name + ", damaging them for &4[DMG] " + damage + "&f, reducing their health to &a[HP] " + (this.isDead() ? 0 : Utils.roundToTwoPlaces(this.health)) + "/" + this.getBaseHealth());
                if (!this.isDead()) { // Player survived attack.
                    if (!Input.getDecision("\n&0- &fWould you like to charge your attack to gain a damage buff? (Y/N)&0: ")) { // Player does not buff.
                        damage = Utils.roundToTwoPlaces(this.strength * (0.5 + random.nextDouble()));
                        target.deductHealth(damage);
                        Utils.outputln("\n&0- &f" + this.name + " attacked " + target.name + ", damaging them for &4[DMG] " + damage + "&f, reducing their health to &a[HP] " + (target.isDead() ? 0 : Utils.roundToTwoPlaces(target.health)) + "/" + target.getBaseHealth());
                    } else { // Player buffs.
                        this.buffStrength(this.strength / 2);
                        Utils.outputln("\n&0- &f" + this.name + " abstained and gained &4[STR] " + (this.strength / 2));
                    }
                    if (target.isDead()) { // Player's attack  killed target.
                        this.health = getBaseHealth();
                        this.strength = getBaseStrength();
                        Utils.outputln("\n&0- &f" + this.name + " killed " + target.name + "! " + incExperience(target.level));
                        incWins();
                    }
                } else { // Target killed player.
                    this.health = getBaseHealth();
                    this.strength = getBaseStrength();
                    Utils.outputln("\n&0- &f" + target.name + " killed " + this.name + "!");
                    incLosses();
                }
            }
        }
    }

    // Name.
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Level.
    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // Experience.
    private int experience = 0;

    public int getExperience() {
        return experience;
    }

    public int getRemainingExperience() {
        return (level * 2) - this.experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String incExperience(int experience) {
        this.experience += experience;
        String output = "&eEXP UP! [EXP] " + this.experience + "/" + (level * 2);
        if (this.experience >= level * 2) {
            levelUp();
            this.experience = 0;
            output = "&eLVL UP! [LVL] " + this.level + " &0// &b[SPD] " + this.speed + " &0// &4[STR] " + this.strength + " &0// &a[HP] " + this.health;
        }
        return output;
    }

    // Fights.
    private int fights, wins, losses;

    private boolean killedBoss;

    public int getFights() {
        return fights;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public boolean hasKilledBoss() {
        return killedBoss;
    }

    public void incWins() {
        fights++;
        wins++;
    }

    public void incLosses() {
        fights++;
        losses++;
    }

    public void setKilledBoss(boolean hasKilledBoss) {
        killedBoss = hasKilledBoss;
    }

    // Statistics.
    private double speed, strength, health;

    public double getSpeed() {
        return speed;
    }

    public double getStrength() {
        return strength;
    }

    public double getHealth() {
        return health;
    }

    public void buffStrength(double amount) {
        this.strength += amount;
    }

    public void deductHealth(double damage) {
        if (damage >= this.health) {
            this.status = Status.DEAD;
        } else {
            this.health -= damage;
        }
    }

    public void levelUp() {
        level++;
        speed++;
        strength++;
        health++;
    }

    // Base stats.
    public abstract double getBaseHealth();

    public abstract double getBaseStrength();

    // String representation.
    @Override
    public String toString() {
        return "&0- &f" + this.name + " &0// &e[LVL] " + this.level + " &0// &b[SPD] " + this.speed + " &0// &4[STR] " + this.strength + " &0// &a[HP] " + this.health;
    }

    public String getFinalStats() {
        return toString() + " &0// &e[FIGHTS] " + this.fights + " &0// &a[WINS] " + this.wins + " &0// &4[LOSSES] " + this.losses;
    }
}
