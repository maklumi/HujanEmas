package com.ulys.lwjgl3

import com.badlogic.gdx.tools.texturepacker.TexturePacker
import com.badlogic.gdx.tools.texturepacker.TexturePacker.process

object AssetPacker {

    private const val input = "lwjgl3/assets-raw"
    private const val output = "assets"

    @JvmStatic
    fun main(args: Array<String>) {
        val settings = TexturePacker.Settings()
        settings.debug = false

        process(settings, "$input/gameplay", "$output/gameplay", "gameplay")

    }
}