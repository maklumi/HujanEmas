package com.ulys

import com.badlogic.gdx.Game
import com.ulys.assets.AssetDescriptors
import kotlinx.coroutines.launch
import ktx.assets.async.AssetStorage
import ktx.async.KtxAsync

class HujanEmas : Game() {
    override fun create() {
        KtxAsync.initiate()
        val assetStorage = AssetStorage()

        KtxAsync.launch {
            assetStorage.loadAsync(AssetDescriptors.GAME_PLAY).await()
            setScreen(FirstScreen(assetStorage))
        }
    }
}