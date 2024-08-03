package com.hieuwu.groceriesstore.core.models

data class OrderModel(
    var id: String = "",
    var status: String? = null,
    var address: String? = null,
    val lineItemList: MutableList<LineItemModel>,
    val createdAt: String = "",
) {
    var totalPrice: Double = 0.0
    val total: Double
        get() = calculateTotal()

    private fun calculateTotal(): Double {
        var sum = 0.0
        for (item in lineItemList) {
            val sub = item.subtotal ?: 0.0
            sum = sum.plus(sub)
        }
        return sum
    }
}
