package com.hieuwu.groceriesstore.domain.usecases.impl

import com.hieuwu.groceriesstore.data.repository.UserRepository
import com.hieuwu.groceriesstore.domain.usecases.SignInUseCase
import java.util.UUID
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import kotlin.random.Random

@RunWith(MockitoJUnitRunner::class)
class SignInUseCaseImplTest {

    @Mock
    lateinit var mockedUserRepository: UserRepository
    private lateinit var testee: SignInUseCase

    @Before
    fun setUp() {
        testee = SignInUseCaseImpl(
            userRepository = mockedUserRepository,
            ioDispatcher = Dispatchers.IO
        )
    }

    @Test
    fun whenExecute_withoutException_thenReturnCorrectValue() {
        val input = SignInUseCase.Input(
            email = UUID.randomUUID().toString(),
            password = UUID.randomUUID().toString()
        )
        val authenticationResult = Random.nextBoolean()

        runBlocking {
            whenever(mockedUserRepository.authenticate(input.email, input.password))
                .thenReturn(authenticationResult)

            val output = testee.execute(input)
            assertEquals(authenticationResult, output.result)
        }
    }

    @Test
    fun whenExecute_withException_thenReturnAccountNotExistedError() {
        val input = SignInUseCase.Input(
            email = UUID.randomUUID().toString(),
            password = UUID.randomUUID().toString()
        )

        runBlocking {
            whenever(mockedUserRepository.authenticate(input.email, input.password))
                .thenThrow(RuntimeException())

            val output = testee.execute(input)
            assertEquals(SignInUseCase.Output.AccountNotExistedError, output)
        }
    }
}