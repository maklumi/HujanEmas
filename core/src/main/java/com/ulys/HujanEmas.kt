package com.ulys

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.ulys.assets.AssetDescriptors
import com.ulys.assets.AssetPaths
import com.ulys.skrin.GameScreen
import com.ulys.skrin.MenuScreen
import kotlinx.coroutines.launch
import ktx.assets.async.AssetStorage
import ktx.assets.toInternalFile
import ktx.async.KtxAsync

class HujanEmas : Game() {

    val assetStorage = AssetStorage()

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        KtxAsync.initiate()

        KtxAsync.launch {
            assetStorage.loadAsync(AssetDescriptors.GAME_PLAY).await()
            val generator = FreeTypeFontGenerator(AssetPaths.UI_FONT.toInternalFile())
            val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
            parameter.size = 32
            val bitmapFont = generator.generateFont(parameter)
            generator.dispose()
            assetStorage.add(AssetDescriptors.FONT, bitmapFont)
            assetStorage.loadAsync(AssetDescriptors.UI_SKIN).await()
            assetStorage.loadAsync(AssetDescriptors.HIT_SOUND).await()
            setScreen(MenuScreen(this@HujanEmas))
        }
    }
}