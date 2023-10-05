package com.hieuwu.groceriesstore.domain.usecases

import com.hieuwu.groceriesstore.data.database.entities.LineItem

interface AddToCartUseCase: SuspendUseCase<AddToCartUseCase.Input, AddToCartUseCase.Output> {
    class Input (val lineItem: LineItem)

    class Output
}
