package com.ulys.util

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.OrthographicCamera

class DebugCamera(private val camera: OrthographicCamera) : EntitySystem() {

    override fun update(deltaTime: Float) {
        DebugCameraController.handleDebugInput(deltaTime)
        DebugCameraController.applyPositionToCamera(camera)
    }

}