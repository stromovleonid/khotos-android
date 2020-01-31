package com.example.feature_login

import com.example.feature_login.presentation.sign_in.model.LoginModelState
import com.example.feature_login.presentation.sign_in.model.LoginStateMapper
import com.example.feature_login.presentation.sign_in.view.LoginViewState
import org.junit.Assert.assertFalse
import org.junit.Test

class LoginStateMapperTest {

    private val mapper: LoginStateMapper = LoginStateMapper()


    @Test
    fun `success state mapping`() {
        val state = LoginModelState("", "", false, null, true)
        assert(mapper.map(state) is LoginViewState.Success)
    }

    @Test
    fun `success after error state mapping`() {
        val state = LoginModelState("", "", false, RuntimeException(), true)
        assert(mapper.map(state) is LoginViewState.Success)
    }

    @Test
    fun `error state mapping`() {
        val state = LoginModelState("", "", false, RuntimeException(), false)
        assert(mapper.map(state) is LoginViewState.Error)
    }

    @Test
    fun `loading state mapping`() {
        val state = LoginModelState("", "", true, null, false)
        assert(mapper.map(state) is LoginViewState.Loading)
    }

    @Test
    fun `invalid login state mapping`() {
        val state = LoginModelState("123", "1234", false, null, false)
        assert(mapper.map(state) is LoginViewState.UserInput)
        assertFalse((mapper.map(state) as LoginViewState.UserInput).isValid)
    }

    @Test
    fun `invalid password state mapping`() {
        val state = LoginModelState("1234", "123", false, null, false)
        assert(mapper.map(state) is LoginViewState.UserInput)
        assertFalse((mapper.map(state) as LoginViewState.UserInput).isValid)
    }

    @Test
    fun `valid input state mapping`() {
        val state = LoginModelState("1234", "1234", false, null, false)
        assert(mapper.map(state) is LoginViewState.UserInput)
        assert((mapper.map(state) as LoginViewState.UserInput).isValid)
    }
}
