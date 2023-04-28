package com.hieuwu.groceriesstore.utilities

object CollectionNames {
    const val products = "products"
    const val categories = "categories"
    const val orders = "orders"
    const val users = "users"
    const val lineItems = "line_items"
}

object UserDocumentProperties {
    const val name = "name"
    const val phone = "phone"
    const val address = "address"
    const val email = "email"
    const val isPromotionNotiEnabled = "isPromotionNotiEnabled"
    const val isOrderNotiEnabled = "isOrderNotiEnabled"
    const val isDatabaseNotiEnabled = "isDatabaseNotiEnabled"
}

object ProductDocumentProperties {
    const val name = "name"
    const val description = "description"
    const val price = "price"
    const val image = "image"
    const val nutrition = "nutrition"
    const val category = "category"
}

object CategoryDocumentProperties {
    const val name = "name"
    const val image = "image"
}

object OrderDocumentProperties {
    const val address = "address"
    const val lineItems = "lineItems"
    const val total = "total"
}

object LineItemDocumentProperties {
    const val quantity = "quantity"
    const val subtotal = "subtotal"
    const val product = "product"
}
