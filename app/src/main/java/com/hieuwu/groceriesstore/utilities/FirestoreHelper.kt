package com.hieuwu.groceriesstore.utilities

import com.google.firebase.firestore.QueryDocumentSnapshot
import com.hieuwu.groceriesstore.domain.entities.Category
import com.hieuwu.groceriesstore.domain.entities.OrderWithLineItems
import com.hieuwu.groceriesstore.domain.entities.Product
import com.hieuwu.groceriesstore.domain.entities.ProductAndLineItem

fun convertItemEntityToDocument(lineItem: ProductAndLineItem): HashMap<String, Any> {
    val document = HashMap<String, Any>()
    document["quantity"] = lineItem.lineItem!!.quantity
    document["subtotal"] = lineItem.lineItem.subtotal
    document["product"] = "products/${lineItem.lineItem.productId}"
    return document
}

fun convertOrderEntityToDocument(order: OrderWithLineItems): HashMap<String, Any> {
    val document = HashMap<String, Any>()
    val lineOrderList = mutableListOf<HashMap<String, Any>>()
    var total = 0.0
    for (item in order.lineItemList) {
        lineOrderList.add(convertItemEntityToDocument(item))
        total += item.lineItem?.subtotal ?: 0.0
    }

    document["address"] = order.order.address
    document["lineItems"] = lineOrderList
    document["total"] = total
    return document
}

fun convertProductDocumentToEntity(document: QueryDocumentSnapshot): Product {
    val id = document.id
    val name: String = document.data["name"] as String
    val description: String = document.data["description"] as String
    val price: Number = document.data["price"] as Number
    val image: String = document.data["image"] as String
    val nutrition: String = document.data["nutrition"] as String
    val category = document.getDocumentReference("category")

    return Product(id, name, description, price.toDouble(), image, category?.id, nutrition)
}

fun convertCategoryDocumentToEntity(document: QueryDocumentSnapshot): Category {
    val id = document.id
    val name: String = document.data["name"] as String
    val image: String = document.data["image"] as String
    return Category(id, name, image)
}
