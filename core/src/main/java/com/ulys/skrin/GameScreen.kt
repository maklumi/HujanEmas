package com.ulys.skrin

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.ulys.HujanEmas
import com.ulys.KilangEntiti
import com.ulys.assets.AssetDescriptors
import com.ulys.common.Pengurus
import com.ulys.config.GameConfig
import com.ulys.listener.DengarLaga
import com.ulys.sistem.*
import com.ulys.util.DebugCamera
import com.ulys.util.RenderDebug
import com.ulys.util.RenderGrid

class GameScreen(private val game: HujanEmas) : Screen {
    private val assetStorage = game.assetStorage
    private val font = assetStorage[AssetDescriptors.FONT]

    private val camera = OrthographicCamera()
    private val viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
    private val hudViewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT)
    private val batch = SpriteBatch()

    private val engine = PooledEngine()
    private val kilang = KilangEntiti(engine, assetStorage)
    private var reset = false
    private val dengarLaga = object : DengarLaga {
        override fun berlaga() {
            Pengurus.bilHayat--
            if (Pengurus.kalah()) {
                Pengurus.updateHighScore()
            } else {
                engine.removeAllEntities()
                reset = true
            }
        }
    }

    private var debug = false

    override fun show() {
        engine.addSystem(HandleInput())
        engine.addSystem(Kedudukan())
        engine.addSystem(HadSisi(viewport))
        engine.addSystem(Bounds())
        engine.addSystem(SpawnEmas(kilang))
        engine.addSystem(Cleanup())
        engine.addSystem(Perlanggaran(dengarLaga))
        engine.addSystem(Pemarkahan())
        engine.addSystem(LukisTekstur(viewport, batch))
        engine.addSystem(RenderHud(hudViewport, batch, font))

        if (debug) {
            engine.addSystem(DebugCamera(camera))
            engine.addSystem(RenderGrid(viewport))
            engine.addSystem(RenderDebug(viewport))
        }
        tambahSemuaEntiti()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        engine.update(delta)

        if (Pengurus.kalah()) {
            Pengurus.reset()
            game.screen = MenuScreen(game)
        }

        if (reset) {
            reset = false
            tambahSemuaEntiti()
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        hudViewport.update(width, height, true)
    }

    private fun tambahSemuaEntiti() {
        kilang.addLatar()
        kilang.addPlayer()
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