package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.data.entities.LineItem

interface AddToCartUseCase: UseCase<AddToCartUseCase.Input, AddToCartUseCase.Output> {
    class Input (val lineItem: LineItem)

    class Output
}