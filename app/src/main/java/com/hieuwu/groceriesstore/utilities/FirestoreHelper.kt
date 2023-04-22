package com.hieuwu.groceriesstore.utilities

import com.hieuwu.groceriesstore.domain.models.LineItemModel
import com.hieuwu.groceriesstore.domain.models.OrderModel

fun convertItemEntityToDocument(lineItem: LineItemModel): HashMap<String, Any> {
    val document = HashMap<String, Any>()
    document[LineItemDocumentProperties.quantity] = lineItem.quantity as Number
    document[LineItemDocumentProperties.subtotal] = lineItem.subtotal as Number
    document[LineItemDocumentProperties.product] =
        "${CollectionNames.products}/${lineItem.productId}"
    return document
}

fun convertOrderEntityToDocument(order: OrderModel): HashMap<String, Any> {
    val document = HashMap<String, Any>()
    val lineOrderList = mutableListOf<HashMap<String, Any>>()
    var total = 0.0
    for (item in order.lineItemList) {
        lineOrderList.add(convertItemEntityToDocument(item))
        total += item.subtotal ?: 0.0
    }

    document[OrderDocumentProperties.address] = order.address as String
    document[OrderDocumentProperties.lineItems] = lineOrderList
    document[OrderDocumentProperties.total] = total
    return document
}
fun createUpdateUserRequest(
    isOrderCreatedEnabled: Boolean,
    isDatabaseRefreshedEnabled: Boolean,
    isPromotionEnabled: Boolean
): HashMap<String, Boolean> {
    val hash = hashMapOf<String, Boolean>()
    hash[UserDocumentProperties.isDatabaseNotiEnabled] = isDatabaseRefreshedEnabled
    hash[UserDocumentProperties.isOrderNotiEnabled] = isOrderCreatedEnabled
    hash[UserDocumentProperties.isPromotionNotiEnabled] = isPromotionEnabled
    return hash
}
