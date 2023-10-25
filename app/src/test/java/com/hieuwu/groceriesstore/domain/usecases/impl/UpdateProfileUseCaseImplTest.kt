package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.domain.usecases.UpdateProfileUseCase
import java.util.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class UpdateProfileUseCaseImplTest {

    @Mock
    lateinit var mockedUserRepository: UserRepository
    private lateinit var testee: UpdateProfileUseCaseImpl

    @Before
    fun setUp() {
        testee = UpdateProfileUseCaseImpl(userRepository = mockedUserRepository)
    }

    @Test
    fun whenExecute_thenCallUserRepository() {
        val input =
            UpdateProfileUseCase.Input(
                userId = UUID.randomUUID().toString(),
                name = UUID.randomUUID().toString(),
                email = UUID.randomUUID().toString(),
                phone = UUID.randomUUID().toString(),
                address = UUID.randomUUID().toString(),
            )

        runBlocking {
            testee.execute(input)

            verify(mockedUserRepository)
                .updateUserProfile(
                    userId = input.userId,
                    name = input.name,
                    email = input.email,
                    phone = input.phone,
                    address = input.address,
                )
        }
    }
}
