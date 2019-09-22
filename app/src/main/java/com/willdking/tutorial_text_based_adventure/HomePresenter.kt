package com.willdking.tutorial_text_based_adventure

import android.provider.Settings.Global.getString
import android.view.View
import java.util.*

class HomePresenter constructor(val homepage: HomeView) {

    // System Elements
    var rand = Random()

    // Game Variables
    val enemies = arrayOf("Skeleton", "Zombie", "Warrior", "Assassin")
    val maxEnemyHealth = 75
    val enemyAttackDamage = 25
    var enemyHealth = 0
    var enemyName = ""

    // Player Variables
    var playerHealth = 100
    val playerAttackDamage = 50
    var numHealthPotions = 3
    val healthPotionHealAmount = 30
    val healthPotionDropChance = 50 // Percentage

    // Combat Variables
    var damageDealt = 0
    var damageReceived = 0

    fun beginGame() {
        homepage.resetGameText()
        homepage.addGameText("Welcome to Text Dungeon.\n")
        homepage.showGameStartButtons()
    }

    fun enterDungeon() {
        homepage.resetGameText()
        homepage.showExploreButtons()
        homepage.addGameText("You have entered the Dungeon.\n")

        playerHealth = 100
        numHealthPotions = 3
    }

    fun endGame() {
        homepage.resetGameText()
        homepage.addGameText("##########################\n"
                + "Thanks for playing!\n"
                + "##########################\n")
        homepage.showGameStartButtons()
    }

    fun newEnemy() {
        enemyName = enemies[rand.nextInt(enemies.size)]
        enemyHealth = rand.nextInt(maxEnemyHealth)

        homepage.addGameText("\t# $enemyName has appeared.\n")

        homepage.showCombatButtons()
        printCombatInfo()
    }

    fun enemyDefeated() {
        homepage.addGameText(" #$enemyName was defeated"
                + "\nYou have $playerHealth health left\n")
        if (rand.nextInt(100) < healthPotionDropChance) {
            numHealthPotions++
            homepage.addGameText("The enemy $enemyName has dropped a health potion.  "
                    + "You have $numHealthPotions(s)\n")
        }
        homepage.addGameText("What would you like to do?\n"
                + "1. Continue Fighting\n"
                + "2. Exit Dungeon\n")
        homepage.showExploreButtons()
    }

    fun playerDefeated() {
        homepage.addGameText("You have taken too much damage. You are too weak to go on.\n"
                + "You limp out of the dungeon, weak from battle.\n")
        homepage.showGameStartButtons()
    }

    fun attackEnemy() {
        damageDealt = rand.nextInt(playerAttackDamage)
        damageReceived = rand.nextInt(enemyAttackDamage)

        enemyHealth -= damageDealt
        playerHealth -= damageReceived

        homepage.addGameText("You strike the enemy for $damageDealt.\n"
                + "You get struck for $damageReceived in retaliation.\n")

        if (playerHealth < 1) {
            playerDefeated()
        } else if (enemyHealth < 1) {
            enemyDefeated()
        } else {
            printCombatInfo()
        }
    }

    fun usePotion() {
        if (numHealthPotions > 0) {
            playerHealth += healthPotionHealAmount
            numHealthPotions--

            homepage.addGameText("You drink a health potion and gain $healthPotionHealAmount HP"
                    + "\n You now have $playerHealth health"
                    + "\n You have $numHealthPotions left\n")
        } else {
            homepage.addGameText("\t>You have no health potions left. Defeat enemies for a chance to get one.\n")
        }

        printCombatInfo()
    }

    fun runAway() {
        homepage.addGameText("You run away from the $enemyName\n"
            + "What would you like to do?\n"
            + "1. Continue Fighting\n"
            + "2. Exit Dungeon\n")
    }

    fun printCombatInfo() {
        homepage.addGameText("\tYour HP: $playerHealth\n"
                + "\t$enemyName's HP: $enemyHealth\n\n"
                + "What would you like to do?\n"
                + "\t1. Attack\n"
                + "\t2. Drink Health Potion\n"
                + "\t3. Run Away!\n")
    }

}