package com.ulys.util

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.log.debug
import ktx.log.logger


object ViewportUtils {

    private val log = logger<ViewportUtils>()
    private const val DEFAULT_CELL_SIZE = 1
    private val renderer = ShapeRenderer()

    fun drawGrid(viewport: Viewport, size: Int = DEFAULT_CELL_SIZE) {
        val cellSize = if (size < DEFAULT_CELL_SIZE) DEFAULT_CELL_SIZE else size

        // copy old color from render
        val oldColor = Color(renderer.color)

        val worldWidth = viewport.worldWidth
        val worldHeight = viewport.worldHeight
        val doubleWorldWidth = worldWidth * 2f
        val doubleWorldHeight = worldHeight * 2f

        renderer.projectionMatrix = viewport.camera.combined
        renderer.begin(ShapeRenderer.ShapeType.Line)
        renderer.color = Color.GRAY

        // draw vertical lines
        var x = -doubleWorldWidth
        while (x < doubleWorldWidth) {
            renderer.line(x, -doubleWorldHeight, x, doubleWorldHeight)
            x += cellSize
        }

        // draw horizontal lines
        var y = -doubleWorldHeight
        while (y < doubleWorldHeight) {
            renderer.line(-doubleWorldWidth, y, doubleWorldWidth, y)
            y += cellSize
        }

        // draw x-y axis lines
        renderer.color = Color.SALMON

        renderer.line(0f, -doubleWorldHeight, 0f, doubleWorldHeight)
        renderer.line(-doubleWorldWidth, 0f, doubleWorldWidth, 0f)

        // draw world bounds
        renderer.color = Color.ROYAL
        renderer.line(0f, worldHeight, worldWidth, worldHeight)
        renderer.line(worldWidth, 0f, worldWidth, worldHeight)

        renderer.end()

        renderer.color = oldColor
    }

    fun debugPixelPerUnit(viewport: Viewport) {
        val screenWidth = viewport.screenWidth.toFloat()
        val screenHeight = viewport.screenHeight.toFloat()

        val worldWidth = viewport.worldWidth
        val worldHeight = viewport.worldHeight

        // PPU => pixels per world unit
        val xPPU = screenWidth / worldWidth
        val yPPU = screenHeight / worldHeight

        log.debug { "x PPU= $xPPU yPPU= $yPPU" }
    }
}