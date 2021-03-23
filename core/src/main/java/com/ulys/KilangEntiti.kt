package com.ulys

import com.badlogic.ashley.core.PooledEngine
import com.ulys.config.GameConfig
import com.ulys.komponen.Bounds
import ktx.ashley.add
import ktx.ashley.entity
import ktx.ashley.with

class KilangEntiti(private val engine: PooledEngine) {

    fun addPlayer() {
        engine.add {
            entity {
                with<Bounds> {
                    val x = (GameConfig.WORLD_WIDTH - GameConfig.PLAYER_SIZE) / 2f
                    val y = 1f - GameConfig.PLAYER_SIZE / 2f
                    this.bounds.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS)
                }
            }
        }
    }

}
