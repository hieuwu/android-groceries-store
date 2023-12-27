package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.domain.usecases.UserSettingsUseCase
import java.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
class UserSettingsUseCaseImplTest {

    @Mock
    lateinit var mockedUserRepository: UserRepository
    private lateinit var testee: UserSettingsUseCase

    @Before
    fun setUp() {
        testee = UserSettingsUseCaseImpl(
            userRepository = mockedUserRepository,
            ioDispatcher = Dispatchers.IO
        )
    }

    @Test
    fun whenExecute_thenCallUserRepository() {
        val input =
            UserSettingsUseCase.Input(
                id = UUID.randomUUID().toString(),
                isOrderCreatedEnabled = Random.nextBoolean(),
                isDatabaseRefreshedEnabled = Random.nextBoolean(),
                isPromotionEnabled = Random.nextBoolean(),
            )

        runBlocking {
            testee.execute(input)

            verify(mockedUserRepository)
                .updateUserSettings(
                    id = input.id,
                    isOrderCreatedEnabled = input.isOrderCreatedEnabled,
                    isDatabaseRefreshedEnabled = input.isDatabaseRefreshedEnabled,
                    isPromotionEnabled = input.isPromotionEnabled,
                )
        }
    }
}
