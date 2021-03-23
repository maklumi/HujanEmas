package com.ulys.sistem

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.Viewport
import com.ulys.common.Mappers
import com.ulys.komponen.Dimensi
import com.ulys.komponen.Posisi
import com.ulys.komponen.Tekstur
import ktx.ashley.allOf
import ktx.ashley.get

class LukisTekstur(
    private val viewport: Viewport,
    private val batch: SpriteBatch
) : EntitySystem() {

    private val family = allOf(Tekstur::class, Posisi::class, Dimensi::class).get()
    private val array = Array<Entity>()

    override fun update(deltaTime: Float) {
        val entities = engine.getEntitiesFor(family)
        entities.forEach { array.add(it) }

        viewport.apply()
        batch.projectionMatrix = viewport.camera.combined
        batch.begin()
        draw()
        batch.end()

        array.clear()
    }

    private fun draw() {
        for (entity in array) {
            val position = entity[Mappers.POSISI] ?: continue
            val dimension = entity[Mappers.DIMENSI] ?: continue
            val texture = entity[Mappers.TEKSTUR] ?: continue
            if (texture.region == null) continue
            batch.draw(
                texture.region!!,
                position.x - dimension.lebar / 2,
                position.y - dimension.tinggi / 2,
                dimension.lebar, dimension.tinggi
            )
        }
    }

}