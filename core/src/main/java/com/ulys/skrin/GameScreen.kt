package com.ulys.skrin

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.ulys.KilangEntiti
import com.ulys.assets.AssetDescriptors
import com.ulys.assets.RegionName
import com.ulys.config.GameConfig
import com.ulys.sistem.Bounds
import com.ulys.sistem.Kedudukan
import com.ulys.sistem.HandleInput
import com.ulys.util.DebugCamera
import com.ulys.util.RenderDebug
import com.ulys.util.RenderGrid
import com.ulys.util.ViewportUtils
import ktx.assets.async.AssetStorage

class GameScreen(assetStorage: AssetStorage) : Screen {

    val gameplay = assetStorage[AssetDescriptors.GAME_PLAY]
    val latar = gameplay.findRegion(RegionName.LATAR)
    val katak = gameplay.findRegion(RegionName.KATAK)
    val emas = gameplay.findRegion(RegionName.EMAS)

    val camera = OrthographicCamera()
    val viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
    val batch = SpriteBatch()

    private val engine = PooledEngine()
    private val kilang = KilangEntiti(engine)

    override fun show() {
        engine.addSystem(HandleInput())
        engine.addSystem(Kedudukan())
        engine.addSystem(Bounds())

        engine.addSystem(DebugCamera(camera))
        engine.addSystem(RenderGrid(viewport))
        engine.addSystem(RenderDebug(viewport))

        kilang.addPlayer()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        engine.update(delta)
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

}