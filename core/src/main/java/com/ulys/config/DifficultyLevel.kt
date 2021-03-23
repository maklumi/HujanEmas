package com.ulys.config

enum class DifficultyLevel(val laju: Float) {
    EASY(GameConfig.LAJU_SLOW),
    MEDIUM(GameConfig.LAJU_MEDIUM),
    HARD(GameConfig.LAJU_SANGAT);

    fun isEasy() = this == EASY
    fun isMedium() = this == MEDIUM
    fun isHard() = this == HARD
}