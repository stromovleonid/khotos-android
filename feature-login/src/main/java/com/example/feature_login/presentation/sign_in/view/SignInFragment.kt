package com.example.feature_login.presentation.sign_in.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.common.mvi.view.BaseFragment
import com.example.common.view.extensions.clicks
import com.example.common.view.extensions.textChanges

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
    BaseFragment<LoginViewEvent, LoginViewState, LoginModelState, SignInViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: SignInViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerLoginComponent.create().inject(this)
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
        state.run {
            loginInput.isErrorEnabled = loginError != null
            loginInput.error = loginError

            passwordInput.isErrorEnabled = loginError != null
            passwordInput.error = passwordError

            authError?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT)
            }
        }
    }

    companion object {
        fun newInstance(): Fragment =
            SignInFragment()
    }
}
