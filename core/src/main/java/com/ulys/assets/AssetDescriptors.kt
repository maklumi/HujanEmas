package com.ulys.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin

object AssetDescriptors {
    val GAME_PLAY = AssetDescriptor(AssetPaths.GAME_PLAY, TextureAtlas::class.java)
    val FONT = AssetDescriptor(AssetPaths.UI_FONT, BitmapFont::class.java)
    val UI_SKIN = AssetDescriptor(AssetPaths.UI_SKIN, Skin::class.java)
}