package com.example.feature_login

import com.example.data.datasources.api.AuthApi
import com.example.data.interactor.TokenInteractor
import com.example.data.utils.DispatchersProviderImpl
import com.example.data.utils.TestUtils.testObserveFlow
import com.example.data.utils.TestUtils.testPause
import com.example.feature_login.presentation.sign_in.intent.LoginIntentFactory
import com.example.feature_login.presentation.sign_in.model.LoginModel
import com.example.feature_login.presentation.sign_in.model.LoginModelState
import com.example.feature_login.presentation.sign_in.view.LoginViewEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@FlowPreview
class LoginModelTest {

    @Mock
    lateinit var authApi: AuthApi

    private val dispatchersProvider = DispatchersProviderImpl()

    @Mock
    lateinit var tokenInteractor: TokenInteractor

    lateinit var factory: LoginIntentFactory

    lateinit var model: LoginModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        model = LoginModel(dispatchersProvider)
        factory = LoginIntentFactory(authApi, dispatchersProvider, tokenInteractor)
    }


    @Test
    fun `login input success`() =
        runBlocking {
            var value: LoginModelState? = null
            val job = testObserveFlow(model.observe()) { value = it }

            model.consume(factory.toIntent(LoginViewEvent.LoginChanged("login")))
            testPause()
            assertTrue(value?.login == "login")
            job.cancel()
        }

    @Test
    fun `password input success`() =
        runBlocking {
            var value: LoginModelState? = null
            val job = testObserveFlow(model.observe()) { value = it }

            model.consume(factory.toIntent(LoginViewEvent.PasswordChanged("pass")))
            testPause()
            assertTrue(value?.password == "pass")
            job.cancel()
        }
}
