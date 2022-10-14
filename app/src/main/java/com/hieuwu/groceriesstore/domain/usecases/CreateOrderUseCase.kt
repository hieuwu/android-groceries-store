package com.hieuwu.groceriesstore.domain.usecases

import androidx.lifecycle.LiveData
import com.hieuwu.groceriesstore.domain.models.OrderModel
import com.hieuwu.groceriesstore.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface CreateOrderUseCase {
    suspend fun sendOrderToServer(order: OrderModel): Boolean

    @Deprecated("Use GetProfileUseCase")
    fun getCurrentUser(): LiveData<UserModel?>

    @Deprecated("Use GetCurrentCartUseCase")
    fun getCurrentCart(): Flow<OrderModel?>
}
