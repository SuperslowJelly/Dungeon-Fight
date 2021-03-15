package io.github.superslowjelly.finalproject;

import io.github.superslowjelly.finalproject.entities.Entity;
import io.github.superslowjelly.finalproject.utilities.Input;
import io.github.superslowjelly.finalproject.utilities.Utils;

public class Main {

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {
        Utils.outputln("\n" +
                "&4▓&e█████▄  █    ██  ███▄    █   ▄████ &4▓&e█████  &4▒&e█████   ███▄    █      █████&4▒&e██&4▓  &e▄████  ██&4░ &e██ ▄▄▄█████&4▓\n" +
                "▒&e██▀ ██▌ ██  &4▓&e██&4▒ &e██ ▀█   █  ██&4▒ &e▀█&4▒▓&e█   ▀ &4▒&e██&4▒  &e██&4▒ &e██ ▀█   █    &4▓&e██   &4▒▓&e██&4▒ &e██&4▒ &e▀█&4▒▓&e██&4░ &e██&4▒▓  &e██&4▒ ▓▒\n" +
                "░&e██   █▌&4▓&e██  &4▒&e██&4░▓&e██  ▀█ ██&4▒▒&e██&4░&e▄▄▄&4░▒&e███   &4▒&e██&4░  &e██&4▒▓&e██  ▀█ ██&4▒   ▒&e████ &4░▒&e██&4▒▒&e██&4░&e▄▄▄&4░▒&e██▀▀██&4░▒ ▓&e██&4░ ▒░\n" +
                "░▓&e█▄   ▌&4▓▓&e█  &4░&e██&4░▓&e██&4▒  &e▐▌██&4▒░▓&e█  ██&4▓▒▓&e█  ▄ &4▒&e██   ██&4░▓&e██&4▒  &e▐▌██&4▒   &4░▓&e█&4▒  ░░&e██&4░░▓&e█  ██&4▓░▓&e█ &4░&e██ &4░ ▓&e██&4▓ ░ \n" +
                "░▒&e████&4▓ ▒▒&e█████&4▓ ▒&e██&4░   ▓&e██&4░░▒▓&e███▀&4▒░▒&e████&4▒░ &e████&4▓▒░▒&e██&4░   ▓&e██&4░   ░▒&e█&4░   ░&e██&4░░▒▓&e███▀&4▒░▓&e█&4▒░&e██&4▓  ▒&e██&4▒ ░ \n" +
                " &4▒▒▓  ▒ ░▒▓▒ ▒ ▒ ░ ▒░   ▒ ▒  ░▒   ▒ ░░ ▒░ ░░ ▒░▒░▒░ ░ ▒░   ▒ ▒     ▒ ░   ░▓   ░▒   ▒  ▒ ░░▒░▒  ▒ ░░   \n" +
                " ░ ▒  ▒ ░░▒░ ░ ░ ░ ░░   ░ ▒░  ░   ░  ░ ░  ░  ░ ▒ ▒░ ░ ░░   ░ ▒░    ░      ▒ ░  ░   ░  ▒ ░▒░ ░    ░    \n" +
                " ░ ░  ░  ░░░ ░ ░    ░   ░ ░ ░ ░   ░    ░   ░ ░ ░ ▒     ░   ░ ░     ░ ░    ▒ ░░ ░   ░  ░  ░░ ░  ░      \n" +
                "   ░       ░              ░       ░    ░  ░    ░ ░           ░            ░        ░  ░  ░  ░         \n" +
                " ░                                                                                                    \n");
        Utils.outputln("&0- &fWelcome to Dungeon Fight! Slaughter endless waves of enemies, and annihilate the boss to win!\n");
        String name = Input.getString("&0- &fPlease enter your name&0: ");
        Entity player = Input.getEntity("\n&0- &fPlease enter your preferred class (Archer, Warrior, or Wizard)&0: ", name);

        boolean gameFlag = true;
        Entity enemy = Utils.randomEnemy(player);
        while (gameFlag) { // Main game loop.
            if (!player.isDead() && !player.hasKilledBoss()) { // If player hasn't died, and hasn't killed the boss.
                player.fight(enemy);
                if (enemy.getName().contains("Boss") && enemy.isDead()) {
                    player.setKilledBoss(true);
                    continue;
                }
                if (player.isDead()) { // If player lost the last fight.
                    if (Input.getDecision("\n&0- &fWould you like to keep fighting? (Y/N)&0: ")) {
                        player.revive();
                        enemy = Utils.randomEnemy(player);
                    } else gameFlag = false;
                } else if (Input.getDecision("\n&0- &fWould you like to face the boss? (Y/N)&0: ")) {
                    enemy = Utils.randomBoss();
                } else { // Player doesn't want to fight the boss.
                    enemy = Utils.randomEnemy(player);
                }
            } else if (player.hasKilledBoss()) { // If the player has killed the boss.
                gameFlag = false; // End the gameloop.
            }
        }
        Utils.clearScreen();
        Utils.outputln(player.hasKilledBoss() ? "&0- &aYOU WIN!\n" : "&0- &4YOU LOSE!\n");
        Utils.outputln(player.getFinalStats());
    }
}
