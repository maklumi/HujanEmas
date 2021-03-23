package com.ulys.common

import com.ulys.komponen.Bounds
import com.ulys.komponen.Laju
import com.ulys.komponen.Posisi
import ktx.ashley.*

object Mappers {

    val BOUNDS = mapperFor<Bounds>()
    val POSISI = mapperFor<Posisi>()
    val LAJU = mapperFor<Laju>()

}