package com.ulys.komponen

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

class Tabrak : Component, Pool.Poolable {

    var tertabrak = false

    override fun reset() {
        tertabrak = false
    }

}