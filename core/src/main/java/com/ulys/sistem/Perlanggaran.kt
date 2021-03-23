package com.ulys.sistem

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.math.Intersector
import com.ulys.common.Mappers
import com.ulys.komponen.Bounds
import com.ulys.komponen.PlayerTag
import com.ulys.komponen.Tabrak
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.log.debug
import ktx.log.logger

class Perlanggaran : EntitySystem() {

    private val log = logger<Perlanggaran>()

    private val familiPemain = allOf(PlayerTag::class, Bounds::class).get()
    private val familiEmas = allOf(Tabrak::class, Bounds::class).get()

    override fun update(deltaTime: Float) {
        val pemain = engine.getEntitiesFor(familiPemain).first() // 1 pemain
        val lisEmas = engine.getEntitiesFor(familiEmas)

        for (emas in lisEmas) {
            val tabrak = emas[Mappers.TABRAK] ?: continue
            if (tabrak.tertabrak) continue

            if (berlaga(pemain, emas)) {
                tabrak.tertabrak = true
                engine.removeEntity(emas)
                log.debug { "berlaga" }
            }
        }
    }

    private fun berlaga(pemain: Entity, emas: Entity): Boolean {
        val boundsPemain = pemain[Mappers.BOUNDS]
        val boundsEmas = emas[Mappers.BOUNDS]
        return Intersector.overlaps(boundsPemain?.bounds, boundsEmas?.bounds)
    }
}