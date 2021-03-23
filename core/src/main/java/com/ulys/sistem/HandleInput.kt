package com.ulys.sistem

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.ulys.common.Mappers
import com.ulys.config.GameConfig
import com.ulys.komponen.Laju
import com.ulys.komponen.PlayerTag
import ktx.ashley.allOf
import ktx.ashley.get

class HandleInput : IteratingSystem(allOf(PlayerTag::class, Laju::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val movement = entity[Mappers.LAJU] ?: return
        movement.xSpeed = 0f

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            movement.xSpeed = GameConfig.MAX_PLAYER_X_SPEED
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            movement.xSpeed = -GameConfig.MAX_PLAYER_X_SPEED
        }
    }

}