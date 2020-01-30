package com.example.feature_login.presentation.sign_in.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.InjectionsHolder
import com.example.common.view.extensions.clicks
import com.example.common.view.extensions.textChanges
import com.example.common.view.fragment.BlockingLoadingFragment

import com.example.feature_login.R
import com.example.feature_login.presentation.sign_in.di.DaggerLoginComponent
import com.example.feature_login.presentation.sign_in.model.LoginModelState
import kotlinx.android.synthetic.main.sign_in_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class SignInFragment :
    BlockingLoadingFragment<LoginViewEvent, LoginViewState, LoginModelState>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: SignInViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerLoginComponent
            .builder()
            .appComponent(InjectionsHolder.appComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_in_fragment, container, false)
    }

    override fun viewEvents(): Flow<LoginViewEvent> {
        val loginChanges = login.textChanges().map { LoginViewEvent.LoginChanged(it) }
        val passwordChanges = password.textChanges().map { LoginViewEvent.PasswordChanged(it) }
        val signIns = signIn.clicks().map { LoginViewEvent.SignInPressed }

        return flowOf(loginChanges, passwordChanges, signIns).flattenMerge(3)
    }

    override fun render(state: LoginViewState) {

        when(state) {
            is LoginViewState.Error -> {
                hideLoading()

                loginInput.isErrorEnabled = state.loginError != null
                loginInput.error = state.loginError

                passwordInput.isErrorEnabled = state.loginError != null
                passwordInput.error = state.passwordError

                signIn.isEnabled = state.isValid
                setInputEnabled(true)
            }

            is LoginViewState.UserInput -> {
                hideLoading()
                signIn.isEnabled = state.isValid
                setInputEnabled(true)
            }

            is LoginViewState.Loading -> {
                showLoading()
                setInputEnabled(false)
            }

            is LoginViewState.Success -> {
                hideLoading()
                setInputEnabled(true)
            }
        }
    }

    private fun setInputEnabled(isEnabled: Boolean) {
        loginInput.isEnabled = isEnabled
        passwordInput.isEnabled = isEnabled
    }

    companion object {
        fun newInstance(): Fragment =
            SignInFragment()
    }
}
