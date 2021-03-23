package com.ulys

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.ulys.assets.AssetDescriptors
import kotlinx.coroutines.launch
import ktx.assets.async.AssetStorage
import ktx.async.KtxAsync

class HujanEmas : Game() {
    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        KtxAsync.initiate()
        val assetStorage = AssetStorage()

        KtxAsync.launch {
            assetStorage.loadAsync(AssetDescriptors.GAME_PLAY).await()
            setScreen(FirstScreen(assetStorage))
        }
    }
}