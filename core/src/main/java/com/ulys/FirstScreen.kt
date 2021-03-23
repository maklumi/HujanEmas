package com.ulys

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.ulys.assets.AssetDescriptors
import com.ulys.assets.RegionName
import com.ulys.config.GameConfig
import com.ulys.util.DebugCameraController
import com.ulys.util.ViewportUtils
import ktx.assets.async.AssetStorage

class FirstScreen(assetStorage: AssetStorage) : Screen {

    val gameplay = assetStorage[AssetDescriptors.GAME_PLAY]
    val latar = gameplay.findRegion(RegionName.LATAR)
    val katak = gameplay.findRegion(RegionName.KATAK)
    val emas = gameplay.findRegion(RegionName.EMAS)

    val camera = OrthographicCamera()
    val viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
    val batch = SpriteBatch()

    override fun show() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.projectionMatrix = camera.combined
        batch.begin()
        batch.draw(latar, 0f, 0f, GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT)
        batch.draw(katak, 1f, 1f, 1f, 1f)
        batch.draw(emas, 2f, 2f, 2f, 2f)
        batch.end()
        debug(delta)
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        ViewportUtils.debugPixelPerUnit(viewport)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        batch.dispose()
    }

    private fun debug(delta: Float) {
        DebugCameraController.handleDebugInput(delta)
        DebugCameraController.applyPositionToCamera(camera)
        ViewportUtils.drawGrid(viewport)
    }
}