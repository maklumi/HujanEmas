package com.ulys.sistem

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.ulys.common.Mappers
import com.ulys.komponen.Laju
import com.ulys.komponen.Posisi
import ktx.ashley.allOf
import ktx.ashley.get

class Kedudukan : IteratingSystem(allOf(Posisi::class, Laju::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val pos = entity[Mappers.POSISI] ?: return
        val laju = entity[Mappers.LAJU] ?: return

        pos.x += laju.xSpeed
        pos.y += laju.ySpeed
    }
}