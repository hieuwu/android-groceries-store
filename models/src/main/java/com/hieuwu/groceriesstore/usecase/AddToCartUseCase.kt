package com.hieuwu.groceriesstore.usecase

interface AddToCartUseCase: SuspendUseCase<AddToCartUseCase.Input, AddToCartUseCase.Output> {
    class Input (
        val id: Long = 0,
        val productId: String,
        val orderId: String,
        var quantity: Int,
        var subtotal: Double
    )

    class Output
}
