package com.ulys.sistem

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.viewport.Viewport
import com.ulys.common.Mappers
import com.ulys.komponen.Dimensi
import com.ulys.komponen.HadSisiTag
import com.ulys.komponen.Posisi
import ktx.ashley.allOf
import ktx.ashley.get

class HadSisi(private val viewport: Viewport) :
    IteratingSystem(allOf(HadSisiTag::class, Posisi::class, Dimensi::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val posisi = entity[Mappers.POSISI] ?: return
        val dim = entity[Mappers.DIMENSI] ?: return
        posisi.x = MathUtils.clamp(posisi.x, dim.lebar / 2, viewport.worldWidth - dim.lebar / 2)
        posisi.y = MathUtils.clamp(posisi.y, 0f, viewport.worldHeight)
    }
}