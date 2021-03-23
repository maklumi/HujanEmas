package com.ulys.skrin

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.ulys.HujanEmas
import com.ulys.assets.AssetDescriptors
import com.ulys.assets.RegionName

class MenuScreen(game: HujanEmas) : SkrinDasar(game) {

    override fun createUI(): Actor {
        val gamePlayAtlas = assetStorage[AssetDescriptors.GAME_PLAY]
        val bgRegion = gamePlayAtlas.findRegion(RegionName.LATAR)
        val uiskin = assetStorage[AssetDescriptors.UI_SKIN]

        val playButton = TextButton("PLAY", uiskin)
        playButton.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                play()
            }
        })

        val highScoreButton = TextButton("HIGH SCORE", uiskin)
        highScoreButton.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                showHighScore()
            }
        })

        val optionsButton = TextButton("OPTIONS", uiskin)
        optionsButton.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                showOptions()
            }
        })

        val quitButton = TextButton("QUIT", uiskin)
        quitButton.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                quit()
            }
        })

        val buttonTable = Table(uiskin).apply {
            defaults().pad(20f)
            setBackground(RegionName.PANEL)
            add(playButton).row()
            add(highScoreButton).row()
            add(optionsButton).row()
            add(quitButton)
            center()
        }

        val table = Table().apply {
            background = TextureRegionDrawable(bgRegion)
        }

        //setup
        table.apply {
            add(buttonTable)
            center()
            setFillParent(true)
            pack()
        }

        return table
    }

    private fun play() {
        game.screen = GameScreen(game)
    }

    private fun showHighScore() {
        game.screen = HighScoreScreen(game)
    }

    private fun showOptions() {
        game.screen = OptionsScreen(game)
    }

    private fun quit() {
        Gdx.app.exit()
    }

}