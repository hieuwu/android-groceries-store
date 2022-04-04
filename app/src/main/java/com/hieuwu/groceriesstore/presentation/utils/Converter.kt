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

    @InverseMethod("stringToEmpTy")
    @JvmStatic
    fun stringToEmpTy(
        value: String?
    ): String? {
        return if (value.isNullOrEmpty()) return "Not signed in" else value
    }
}
