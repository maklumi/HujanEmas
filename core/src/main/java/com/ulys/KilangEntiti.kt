package com.ulys

import com.badlogic.ashley.core.PooledEngine
import com.ulys.config.GameConfig
import com.ulys.komponen.*
import ktx.ashley.add
import ktx.ashley.entity
import ktx.ashley.with

class KilangEntiti(private val engine: PooledEngine) {

    fun addPlayer() {
        engine.add {
            entity {
                val x = (GameConfig.WORLD_WIDTH - GameConfig.PLAYER_SIZE) / 2f
                val y = 1f - GameConfig.PLAYER_SIZE / 2f

                with<Bounds> {
                    this.bounds.set(x, y, GameConfig.PLAYER_BOUNDS_RADIUS)
                }
                with<PlayerTag>()
                with<Posisi> {
                    this.x = x
                    this.y = y
                }
                with<Laju>()
                with<HadSisiTag>()
            }
        }
    }

    fun addEmas(x: Float, y: Float) {
        engine.add {
            entity {
                with<Bounds> {
                    this.bounds.set(x, y, GameConfig.EMAS_BOUNDS_RADIUS)
                }
                with<Posisi> {
                    this.x = x
                    this.y = y
                }
                with<Laju> {
                    this.ySpeed = -GameConfig.LAJU_SLOW
                }
                with<CleanTag>()
                with<Tabrak>()
            }
        }
    }

}
