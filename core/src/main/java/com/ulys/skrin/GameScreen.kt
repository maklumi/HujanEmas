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
import com.ulys.common.Pengurus
import com.ulys.config.GameConfig
import com.ulys.listener.DengarLaga
import com.ulys.sistem.*
import com.ulys.util.DebugCamera
import com.ulys.util.RenderDebug
import com.ulys.util.RenderGrid
import ktx.assets.async.AssetStorage

class GameScreen(assetStorage: AssetStorage) : Screen {

    val gameplay = assetStorage[AssetDescriptors.GAME_PLAY]
    val latar = gameplay.findRegion(RegionName.LATAR)
    val katak = gameplay.findRegion(RegionName.KATAK)
    val emas = gameplay.findRegion(RegionName.EMAS)
    val font = assetStorage[AssetDescriptors.FONT]

    val camera = OrthographicCamera()
    val viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
    val hudViewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT)
    val batch = SpriteBatch()

    private val engine = PooledEngine()
    private val kilang = KilangEntiti(engine)
    private var reset = false
    private val dengarLaga = object : DengarLaga {
        override fun berlaga() {
            Pengurus.bilHayat--
            if (Pengurus.kalah()) {
                // simpan markah
            } else {
                engine.removeAllEntities()
                reset = true
            }
        }
    }

    override fun show() {
        engine.addSystem(HandleInput())
        engine.addSystem(Kedudukan())
        engine.addSystem(HadSisi(viewport))
        engine.addSystem(Bounds())
        engine.addSystem(SpawnEmas(kilang))
        engine.addSystem(Cleanup())
        engine.addSystem(Perlanggaran(dengarLaga))
        engine.addSystem(RenderHud(hudViewport, batch, font))

        engine.addSystem(DebugCamera(camera))
        engine.addSystem(RenderGrid(viewport))
        engine.addSystem(RenderDebug(viewport))

        tambahSemuaEntiti()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        engine.update(delta)

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