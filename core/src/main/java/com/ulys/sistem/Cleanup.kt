package com.ulys.sistem

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.ulys.common.Mappers
import com.ulys.config.GameConfig
import com.ulys.komponen.CleanTag
import com.ulys.komponen.Posisi
import ktx.ashley.allOf
import ktx.ashley.get

class Cleanup : IteratingSystem(allOf(CleanTag::class, Posisi::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val pos = entity[Mappers.POSISI] ?: return
        if (pos.y < -GameConfig.SAIZ_EMAS) {
            engine.removeEntity(entity)
        }
    }
}