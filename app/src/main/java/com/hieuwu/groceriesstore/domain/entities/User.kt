package com.hieuwu.groceriesstore.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hieuwu.groceriesstore.domain.models.UserModel

@Entity

class User(
    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "address")
    var address: String?,

    @ColumnInfo(name = "phone")
    var phone: String?,
)

fun User.asDomainModel(): UserModel {
    return UserModel(
        id = this.id,
        name = this.name,
        phone = this.phone ?: "",
        email = this.email,
        address = this.address ?: ""
    )
}