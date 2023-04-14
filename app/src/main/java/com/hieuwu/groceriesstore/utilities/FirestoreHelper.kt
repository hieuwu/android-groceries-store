package com.hieuwu.groceriesstore.utilities

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.hieuwu.groceriesstore.data.database.entities.Category
import com.hieuwu.groceriesstore.data.database.entities.Product
import com.hieuwu.groceriesstore.data.database.entities.User
import com.hieuwu.groceriesstore.data.network.dto.UserDto
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

fun convertUserDocumentToEntity(id: String, document: DocumentSnapshot): User {
    val name = document.data?.get(UserDocumentProperties.name)?.toString() ?: ""
    val phone = document.data?.get(UserDocumentProperties.phone)?.toString()
    val address = document.data?.get(UserDocumentProperties.address)?.toString()
    val email = document.data?.get(UserDocumentProperties.email)?.toString() ?: ""
    return User(
        id = id,
        name = name,
        email = email,
        address = address,
        phone = phone,
        isOrderCreatedNotiEnabled = false,
        isPromotionNotiEnabled = false,
        isDataRefreshedNotiEnabled = false
    )
}

fun mapDtoToEntity(user: UserDto): User {
    return User(
        id = user.id,
        name = user.name,
        email = user.email,
        address = user.address,
        phone = user.phone,
        isDataRefreshedNotiEnabled = user.isDataRefreshedNotiEnabled,
        isOrderCreatedNotiEnabled = user.isOrderCreatedNotiEnabled,
        isPromotionNotiEnabled = user.isPromotionNotiEnabled,
    )
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
