package com.ulys.lwjgl3

import kotlin.jvm.JvmStatic
import com.ulys.lwjgl3.Lwjgl3Launcher
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.ulys.HujanEmas
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.ulys.config.GameConfig

/** Launches the desktop (LWJGL3) application.  */
object Lwjgl3Launcher {
    @JvmStatic
    fun main(args: Array<String>) {
        createApplication()
    }

    private fun createApplication(): Lwjgl3Application {
        return Lwjgl3Application(HujanEmas(), defaultConfiguration())
    }

    private fun defaultConfiguration(): Lwjgl3ApplicationConfiguration {
        return Lwjgl3ApplicationConfiguration().apply {
            setTitle("Hujan Emas")
            setWindowedMode(GameConfig.LEBAR, GameConfig.TINGGI)
            setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png")
        }
    }
}