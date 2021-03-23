package com.ulys.util

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.utils.viewport.Viewport

class RenderGrid(
    private val viewport: Viewport
) : EntitySystem() {


    override fun update(deltaTime: Float) {
        viewport.apply() // don't center camera
        ViewportUtils.drawGrid(viewport)
    }

}