package com.hieuwu.groceriesstore.presentation.utils


object Converter {

    fun stringToEmpTy(value: String?): String {
        return if (value.isNullOrEmpty()) return "Not signed in" else value
    }

}
