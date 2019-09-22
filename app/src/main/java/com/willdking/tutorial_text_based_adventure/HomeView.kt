package com.willdking.tutorial_text_based_adventure

interface HomeView {
    fun addGameText(textToAdd: String)

    fun resetGameText()

    fun showCombatButtons()

    fun showExploreButtons()

    fun showGameStartButtons()
}
