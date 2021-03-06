package com.ulys.sistem

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import com.ulys.common.Pengurus
import com.ulys.config.GameConfig

class RenderHud(
    private val viewport: Viewport,
    private val batch: SpriteBatch,
    private val font: BitmapFont
) : EntitySystem() {

    private val layout = GlyphLayout()

    override fun update(deltaTime: Float) {
        viewport.apply()
        batch.projectionMatrix = viewport.camera.combined
        batch.begin()
        draw()
        batch.end()
    }

    private fun draw() {
        val livesString = "BIL HAYAT: " + Pengurus.bilHayat
        layout.setText(font, livesString)
        font.draw(batch, livesString, 20f, GameConfig.HUD_HEIGHT - layout.height)

        val scoreString = String.format("MARKAH: %10d", Pengurus.markah)
        layout.setText(font, scoreString)
        font.draw(batch, scoreString, GameConfig.HUD_WIDTH - 270f, GameConfig.HUD_HEIGHT - layout.height)
    }

}
