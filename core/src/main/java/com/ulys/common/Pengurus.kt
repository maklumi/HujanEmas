package com.ulys.common

import com.badlogic.gdx.Gdx
import com.ulys.HujanEmas
import com.ulys.config.DifficultyLevel
import com.ulys.config.GameConfig

object Pengurus {

    var bilHayat = GameConfig.BIL_HAYAT
    var markah = 0

    private const val HIGH_SCORE_KEY = "highScore"
    private const val DIFFICULTY_KEY = "difficulty"

    // preferences is saved in HomePC/.prefs
    private val pref = Gdx.app.getPreferences(HujanEmas::class.java.simpleName)
    var highScore = pref.getInteger(HIGH_SCORE_KEY, 0)
    private var difficulty = pref.getString(DIFFICULTY_KEY, DifficultyLevel.MEDIUM.name)
    var difficultyLevel = DifficultyLevel.valueOf(difficulty)

    fun updateHighScore() {
        if (markah < highScore) return
        highScore = markah
        pref.putInteger(HIGH_SCORE_KEY, highScore)
        pref.flush() // commit to disk. ie flush from memory to disk
    }

    fun updateDifficulty(newDifficultyLevel: DifficultyLevel) {
        if (difficultyLevel == newDifficultyLevel) return
        difficultyLevel = newDifficultyLevel
        pref.putString(DIFFICULTY_KEY, difficultyLevel.name)
        pref.flush()
    }

    fun kalah(): Boolean {
        return bilHayat <= 0
    }

    fun reset() {
        bilHayat = GameConfig.BIL_HAYAT
        markah = 0
    }

    fun updateScore(amount: Int) {
        markah += amount
    }
}