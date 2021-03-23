package com.ulys.skrin

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.ulys.HujanEmas
import com.ulys.config.GameConfig

abstract class SkrinDasar(val game: HujanEmas) : ScreenAdapter() {

    val viewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT)
    val assetStorage = game.assetStorage
    val batch = SpriteBatch()
    val stage = Stage(viewport, batch)

    abstract fun createUI(): Actor

    override fun show() {
        Gdx.input.inputProcessor = stage

        stage.addActor(createUI())
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        stage.dispose()
        batch.dispose()
    }
}