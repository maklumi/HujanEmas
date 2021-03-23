package com.ulys

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.ulys.assets.AssetDescriptors
import com.ulys.assets.AssetPaths
import com.ulys.skrin.GameScreen
import kotlinx.coroutines.launch
import ktx.assets.async.AssetStorage
import ktx.assets.toInternalFile
import ktx.async.KtxAsync

class HujanEmas : Game() {
    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        KtxAsync.initiate()
        val assetStorage = AssetStorage()

        KtxAsync.launch {
            assetStorage.loadAsync(AssetDescriptors.GAME_PLAY).await()
            val generator = FreeTypeFontGenerator(AssetPaths.UI_FONT.toInternalFile())
            val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
            parameter.size = 32
            val bitmapFont = generator.generateFont(parameter)
            generator.dispose()
            assetStorage.add(AssetDescriptors.FONT, bitmapFont)
            setScreen(GameScreen(assetStorage))
        }
    }
}