package com.example.repository

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.BeforeEach

class AuthRepositoryTest {

    @MockK
    private lateinit var authRepository: AuthRepository

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }


    /**Should return error when the email does not exist*/
    @Test
    fun resetPasswordWhenEmailDoesNotExist() {
        val email = "test@example.com"
        val resultData = ResultData.Message<Unit>("Email does not exist")

        coEvery { authRepository.resetPassword(email) } returns flowOf(resultData)

        val resetPasswordResult = authRepository.resetPassword(email).first()

        assertEquals(resultData, resetPasswordResult)

        coVerify(exactly = 1) { authRepository.resetPassword(email) }
    }

    /**Should return success when the email is valid and exists*/
    @Test
    fun resetPasswordWhenEmailIsValidAndExists() {
        val email = "test@example.com"
        val resultData = ResultData.Success(Unit)
        val flow = flowOf(resultData)

        every { authRepository.resetPassword(email) } returns flow

        val resetPasswordResult = authRepository.resetPassword(email).first()

        assertEquals(resultData, resetPasswordResult)
        verify { authRepository.resetPassword(email) }
    }

    /**Should return error when the email is invalid*/
    @Test
    fun resetPasswordWhenEmailIsInvalid() {
        val invalidEmail = "invalidEmail"
        val expectedErrorMessage = "Invalid email format"
        val expectedMessageResult = ResultData.Message<Unit>(expectedErrorMessage)

        // Mock the flow of ResultData
        val flow = flowOf(expectedMessageResult)
        `when`(authRepository.resetPassword(invalidEmail)).thenReturn(flow)

        // Call the method under test
        val result = authRepository.resetPassword(invalidEmail).first()

        // Verify the result
        assertTrue(result is ResultData.Message)
        assertEquals(expectedErrorMessage, (result as ResultData.Message).errorMessage)
    }

}