package com.hieuwu.groceriesstore.presentation.utils

import androidx.databinding.InverseMethod

object Converter {
    @InverseMethod("intToString")
    @JvmStatic
    fun intToString(
        value: Int
    ): String {
        return value.toString()
    }

    @JvmStatic
    fun stringToInt(
        value: String
    ): Int {
        return value.toInt()
    }
}