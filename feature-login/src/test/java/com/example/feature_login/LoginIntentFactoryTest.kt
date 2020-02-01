package com.example.feature_login

import com.example.common.mvi.model.BaseChannelModel
import com.example.common.mvi.model.Model
import com.example.data.datasources.api.AuthApi
import com.example.data.interactors.token.TokenInteractor
import com.example.data.model.dto.AuthResponse
import com.example.data.model.dto.UserMetadataResponse
import com.example.data.utils.DispatchersProviderImpl
import com.example.data.utils.TestUtils.testObserveFlow
import com.example.data.utils.TestUtils.testPause
import com.example.feature_login.presentation.sign_in.intent.LoginIntentFactory
import com.example.feature_login.presentation.sign_in.model.LoginModelState
import com.example.feature_login.presentation.sign_in.view.LoginViewEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.util.*

@ExperimentalCoroutinesApi
@FlowPreview
class LoginIntentFactoryTest {

    @Mock
    lateinit var authApi: AuthApi

    private val dispatchersProvider = DispatchersProviderImpl()

    @Mock
    lateinit var tokenInteractor: TokenInteractor

    lateinit var factory: LoginIntentFactory

    lateinit var model: Model<LoginModelState>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        doNothing().`when`(tokenInteractor).saveToken(ArgumentMatchers.anyString())

        model = BaseChannelModel(LoginModelState.default(), dispatchersProvider)
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

    @Test
    fun `sign in success`() =
        runBlocking {

            `when`(
                authApi.login(
                    ArgumentMatchers.anyString(),
                    ArgumentMatchers.anyString()
                )
            ).thenReturn(
                Response.success(
                    AuthResponse(
                        "token",
                        UserMetadataResponse(1L, "", Date(), null)
                    )
                )
            )

            var value: LoginModelState? = null
            val job = testObserveFlow(model.observe()) { value = it }

            model.consume(factory.toIntent(LoginViewEvent.SignInPressed))
            testPause()
            assertTrue(value?.isSuccess == true)
            verify(tokenInteractor, times(1)).saveToken("token")
            job.cancel()
        }

    @Test
    fun `sign in failure`() =
        runBlocking {

            `when`(
                authApi.login(
                    ArgumentMatchers.anyString(),
                    ArgumentMatchers.anyString()
                )
            ).thenReturn(
                Response.error(
                    401, "".toResponseBody()
                )
            )

            var value: LoginModelState? = null
            val job = testObserveFlow(model.observe()) { value = it }

            model.consume(factory.toIntent(LoginViewEvent.SignInPressed))
            testPause()
            assertTrue(value?.isSuccess == false)
            assertTrue(value?.error != null)
            job.cancel()
        }
}
