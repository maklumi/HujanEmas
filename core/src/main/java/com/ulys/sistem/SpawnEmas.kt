package com.ulys.sistem

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.math.MathUtils
import com.ulys.KilangEntiti
import com.ulys.config.GameConfig

class SpawnEmas(private val kilangEntiti: KilangEntiti) :
    IntervalSystem(GameConfig.TEMPOH_SPAWN_EMAS) {

    override fun updateInterval() {
        val emasX = MathUtils.random(0f, GameConfig.WORLD_WIDTH - GameConfig.SAIZ_EMAS)
        val emasY = GameConfig.WORLD_HEIGHT
        kilangEntiti.addEmas(emasX, emasY)
    }
}