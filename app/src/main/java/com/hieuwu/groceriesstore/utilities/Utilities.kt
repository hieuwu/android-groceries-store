package com.hieuwu.groceriesstore.utilities


enum class OrderStatus(val value: String) { IN_CART("cart"), COMPLETED("completed") }
enum class ProductListMode(val value: String) { CATEGORY("category"), DEFAULT("product") }
enum class FilterOrder (val value: String) { ASC("asc"), DESC("desc") }

const val PRODUCT_TABLE = "product"
const val CATEGORY_TABLE = "category"
const val PROFILE_TABLE = "profile"
const val ORDER_TABLE = "order"
const val LINE_ITEM_TABLE = "lineItem"
const val DATABASE_NAME = ""

object KeyData {
    const val RESULT_KEY = "CHECKOUT_TO_DELIVERY_UPDATE"
    const val NAME_KEY = "Name"
    const val PHONE_KEY = "Phone"
    const val STREET_KEY = "Street"
    const val WARD_KEY = "Ward"
    const val CITY_KEY = "City"
}