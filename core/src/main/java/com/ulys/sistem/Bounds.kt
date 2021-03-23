package com.ulys.sistem

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.ulys.common.Mappers
import com.ulys.komponen.Bounds
import com.ulys.komponen.Posisi
import ktx.ashley.allOf
import ktx.ashley.get

class Bounds : IteratingSystem(allOf(Bounds::class, Posisi::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val bounds = entity[Mappers.BOUNDS] ?: return
        val pos = entity[Mappers.POSISI] ?: return
        bounds.bounds.x = pos.x
        bounds.bounds.y = pos.y
    }

}