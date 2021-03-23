package com.ulys.skrin

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.ulys.HujanEmas
import com.ulys.assets.AssetDescriptors
import com.ulys.assets.RegionName
import com.ulys.common.Pengurus


class HighScoreScreen(game: HujanEmas) : SkrinDasar(game) {
    override fun createUI(): Actor {
        val gamePlayAtlas = assetStorage[AssetDescriptors.GAME_PLAY]
        val uiSkin = assetStorage[AssetDescriptors.UI_SKIN]

        val bgRegion = gamePlayAtlas.findRegion(RegionName.LATAR)

        val highScoreText = Label("HIGHSCORE", uiSkin)
        val highScoreString = Pengurus.highScore.toString()
        val highScoreLabel = Label(highScoreString, uiSkin)

        val backButton = TextButton("BACK", uiSkin).apply {
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    back()
                }
            })
        }

        val contentTable = Table(uiSkin).apply {
            defaults().pad(20f)
            setBackground(RegionName.PANEL)
            add(highScoreText).row()
            add(highScoreLabel).row()
            add(backButton)
            center()
        }

        // background -> high score text ->label -> back button -> setup table

        return Table().apply {
            background = TextureRegionDrawable(bgRegion)
            add(contentTable)
            center()
            setFillParent(true)
            pack()
        }
    }

    private fun back() {
        game.screen = MenuScreen(game)
    }
}