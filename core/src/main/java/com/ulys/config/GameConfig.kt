package com.ulys.config

object GameConfig {

    // dalam piksel
    const val LEBAR = 480
    const val TINGGI = 800
    const val HUD_WIDTH = 480f
    const val HUD_HEIGHT = 800f

    // dalam kaki
    const val WORLD_WIDTH = 6f
    const val WORLD_HEIGHT = 10f

    const val PLAYER_BOUNDS_RADIUS = 0.4f
    const val PLAYER_SIZE = PLAYER_BOUNDS_RADIUS * 2
    const val MAX_PLAYER_X_SPEED = 0.25f

    const val EMAS_BOUNDS_RADIUS = 0.3f
    const val SAIZ_EMAS = EMAS_BOUNDS_RADIUS * 2
    const val TEMPOH_SPAWN_EMAS = 2f

    const val LAJU_SLOW = 0.05f
    const val LAJU_MEDIUM = 0.10f
    const val LAJU_SANGAT = 0.15f
    const val BIL_HAYAT = 3
    const val SELANG_MARKAH = 0.25f
}