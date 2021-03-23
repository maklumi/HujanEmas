package com.ulys

import com.badlogic.ashley.core.PooledEngine
import com.ulys.assets.AssetDescriptors
import com.ulys.assets.RegionName
import com.ulys.config.GameConfig
import com.ulys.komponen.*
import ktx.ashley.add
import ktx.ashley.entity
import ktx.ashley.with
import ktx.assets.async.AssetStorage

class KilangEntiti(
    private val engine: PooledEngine,
    assetStorage: AssetStorage
) {
    val gameplay = assetStorage[AssetDescriptors.GAME_PLAY]
    val latar = gameplay.findRegion(RegionName.LATAR)
    val katak = gameplay.findRegion(RegionName.KATAK)
    val emas = gameplay.findRegion(RegionName.EMAS)

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
                with<Tekstur> { this.region = katak }
                with<Dimensi> {
                    this.lebar = GameConfig.PLAYER_SIZE
                    this.tinggi = GameConfig.PLAYER_SIZE
                }
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
                with<Tekstur> { this.region = emas }
                with<Dimensi> {
                    this.lebar = GameConfig.SAIZ_EMAS
                    this.tinggi = GameConfig.SAIZ_EMAS
                }
            }
        }
    }

    fun addLatar() {
        engine.add {
            entity {
                with<Tekstur> { region = latar }
                with<Posisi> {
                    x = GameConfig.WORLD_WIDTH / 2f
                    y = GameConfig.WORLD_HEIGHT / 2f
                }
                with<Dimensi> {
                    lebar = GameConfig.WORLD_WIDTH
                    tinggi = GameConfig.WORLD_HEIGHT
                }
            }
        }
    }

}
