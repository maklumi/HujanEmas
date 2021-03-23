package com.ulys.util

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.Viewport
import com.ulys.common.Mappers
import com.ulys.komponen.Bounds
import ktx.ashley.allOf
import ktx.ashley.get

class RenderDebug(
    private val viewport: Viewport
) : IteratingSystem(allOf(Bounds::class).get()) {

    private val renderer = ShapeRenderer()

    override fun update(deltaTime: Float) {
        val oldColor = renderer.color.cpy()

        viewport.apply()
        renderer.projectionMatrix = viewport.camera.combined

        renderer.begin(ShapeRenderer.ShapeType.Line)
        renderer.color = Color.RED

        super.update(deltaTime)

        renderer.end()
        renderer.color = oldColor
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val bc = entity[Mappers.BOUNDS] ?: return
        renderer.circle(bc.bounds.x, bc.bounds.y, bc.bounds.radius, 30)
    }

}
