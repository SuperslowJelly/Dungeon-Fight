package io.github.superslowjelly.finalproject.utilities;

import io.github.superslowjelly.finalproject.entities.Archer;
import io.github.superslowjelly.finalproject.entities.Entity;
import io.github.superslowjelly.finalproject.entities.Warrior;
import io.github.superslowjelly.finalproject.entities.Wizard;

import java.util.Random;

public class Utils {

    public static double roundToTwoPlaces(double input) {
        return Math.round(input * 100.0) / 100.0;
    }

    public static Entity randomEnemy(Entity player) {
        switch (new Random().nextInt((3 - 1) + 1) + 1) {
            case 1:
                return new Archer(player.getLevel() - 1);
            case 2:
                return new Warrior(player.getLevel() - 1);
            case 3:
                return new Wizard(player.getLevel() - 1);
            default:
                return null;
        }
    }

    public static Entity randomBoss() {
        int bossLevel = randInt(7, 14);
        switch (randInt(1, 3)) {
            case 1:
                return new Archer("Boss", bossLevel);
            case 2:
                return new Warrior("Boss", bossLevel);
            case 3:
                return new Wizard("Boss", bossLevel);
            default:
                return null;
        }
    }

    public static int randInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {}
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) System.out.println("");
    }

    public static String black = "\u001b[30m", red = "\u001b[31m", green = "\u001b[32m", yellow = "\u001b[33m", blue = "\u001b[34m", magenta = "\u001b[35m", cyan = "\u001b[36m", white = "\u001b[37m", reset = "\u001b[0m";

    public static void outputln(String text) {
        System.out.println(text.replace("&0", black)
                .replace("&4", red)
                .replace("&a", green)
                .replace("&e", yellow)
                .replace("&9", blue)
                .replace("&d", magenta)
                .replace("&b", cyan)
                .replace("&f", white)
                .replace("&r", reset) + reset
        );
        Utils.pause();
    }

    public static void output(String text) {
        System.out.print(text.replace("&0", black)
                .replace("&4", red)
                .replace("&a", green)
                .replace("&e", yellow)
                .replace("&9", blue)
                .replace("&d", magenta)
                .replace("&b", cyan)
                .replace("&f", white)
                .replace("&r", reset) + reset
        );
        Utils.pause();
    }
}
