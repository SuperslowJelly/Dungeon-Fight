package io.github.superslowjelly.finalproject.utilities;

import io.github.superslowjelly.finalproject.entities.Archer;
import io.github.superslowjelly.finalproject.entities.Entity;
import io.github.superslowjelly.finalproject.entities.Warrior;
import io.github.superslowjelly.finalproject.entities.Wizard;

import java.util.Scanner;

public class Input {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static void error() {
        Utils.outputln("&0- &4ERROR! &fPlease try again!");
    }

    // Prompt the user for a string.
    public static String getString(String output) {
        Utils.output(output);
        String input = SCANNER.nextLine();
        if (!input.equals(""))
            return input;
        else {
            error();
            return getString(output);
        }
    }

    // Prompt the user for an urgency level.
    public static Entity getEntity(String output, String name) {
        Utils.output(output);
        String input = SCANNER.nextLine();
        switch (input.toUpperCase()) {
            case "ARCHER":
                return new Archer(name);
            case "WARRIOR":
                return new Warrior(name);
            case "WIZARD":
                return new Wizard(name);
            default:
                error();
                return getEntity(output, name);
        }
    }

    // Prompt the user for a decision.
    public static boolean getDecision(String output) {
        Utils.output(output);
        String input = SCANNER.nextLine();
        if (input.equalsIgnoreCase("Y"))
            return true;
        else if (input.equalsIgnoreCase("N")) {
            return false;
        } else {
            error();
            return getDecision(output);
        }
    }
}
