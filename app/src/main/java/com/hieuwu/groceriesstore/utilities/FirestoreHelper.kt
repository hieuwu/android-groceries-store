package com.hieuwu.groceriesstore.utilities

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.hieuwu.groceriesstore.data.entities.Category
import com.hieuwu.groceriesstore.data.entities.Product
import com.hieuwu.groceriesstore.data.entities.User
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
        total += item?.subtotal ?: 0.0
    }

    document[OrderDocumentProperties.address] = order.address as String
    document[OrderDocumentProperties.lineItems] = lineOrderList
    document[OrderDocumentProperties.total] = total
    return document
}

fun convertProductDocumentToEntity(document: QueryDocumentSnapshot): Product {
    val id = document.id
    val name: String = document.data[ProductDocumentProperties.name] as String
    val description: String = document.data[ProductDocumentProperties.description] as String
    val price: Number = document.data[ProductDocumentProperties.price] as Number
    val image: String = document.data[ProductDocumentProperties.image] as String
    val nutrition: String = document.data[ProductDocumentProperties.nutrition] as String
    val category = document.getDocumentReference(ProductDocumentProperties.category)

    return Product(id, name, description, price.toDouble(), image, category?.id, nutrition)
}

fun convertCategoryDocumentToEntity(document: QueryDocumentSnapshot): Category {
    val id = document.id
    val name: String = document.data[CategoryDocumentProperties.name] as String
    val image: String = document.data[CategoryDocumentProperties.image] as String
    return Category(id, name, image)
}

fun convertUserDocumentToEntity(id: String, document: DocumentSnapshot): User {
    val name: String = document.data?.get(UserDocumentProperties.name)!! as String
    val phone: String = document.data?.get(UserDocumentProperties.phone) as String
    val address: String = document.data?.get(UserDocumentProperties.address) as String
    val email: String = document.data?.get(UserDocumentProperties.email) as String
    return User(id, name, email, address, phone, false, false, false)
}

fun convertUserEntityToDocument(user: User): HashMap<String, String?> {
    return hashMapOf(
        UserDocumentProperties.name to user.name,
        UserDocumentProperties.email to user.email,
        UserDocumentProperties.phone to user.phone,
        UserDocumentProperties.address to user.address
    )
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
