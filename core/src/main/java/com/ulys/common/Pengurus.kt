package com.ulys.common

import com.ulys.config.GameConfig

object Pengurus {

    var bilHayat = GameConfig.BIL_HAYAT
    var markah = 0

    fun kalah(): Boolean {
        return bilHayat <= 0
    }


}