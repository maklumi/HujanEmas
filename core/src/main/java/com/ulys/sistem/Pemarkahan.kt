package com.ulys.sistem

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.math.MathUtils
import com.ulys.common.Pengurus
import com.ulys.config.GameConfig

class Pemarkahan : IntervalSystem(GameConfig.SELANG_MARKAH) {

    override fun updateInterval() {
        Pengurus.updateScore(MathUtils.random(1, 5))
    }
}