package com.willdking.tutorial_text_based_adventure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HomeView {

    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = HomePresenter(this)

        button_attack.setOnClickListener { presenter.attackEnemy() }
        button_potion.setOnClickListener { presenter.usePotion() }
        button_run.setOnClickListener { presenter.runAway() }

        button_explore.setOnClickListener { presenter.newEnemy() }
        button_exit.setOnClickListener { presenter.endGame() }

        button_game_start.setOnClickListener { presenter.enterDungeon() }

        presenter.beginGame()
    }

    override fun addGameText(textToAdd: String) {
        game_text_window.append("\n" + textToAdd)
        game_text.fullScroll(View.FOCUS_DOWN)
    }

    override fun resetGameText() {
        game_text_window.text = ""
    }

    override fun showCombatButtons() {
        game_combat_buttons.visibility = View.VISIBLE
        game_explore_buttons.visibility = View.GONE
        button_game_start.visibility = View.GONE
    }

    override fun showExploreButtons() {
        game_combat_buttons.visibility = View.GONE
        game_explore_buttons.visibility = View.VISIBLE
        button_game_start.visibility = View.GONE
    }

    override fun showGameStartButtons() {
        game_combat_buttons.visibility = View.GONE
        game_explore_buttons.visibility = View.GONE
        button_game_start.visibility = View.VISIBLE
    }
}
