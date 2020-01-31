package com.example.feature_splash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.lifecycle.ViewModelProvider
import com.example.common.di.InjectionsHolder
import com.example.common.mvi.view.BaseFragment
import com.example.feature_splash.R
import com.example.feature_splash.di.DaggerSplashComponent
import kotlinx.android.synthetic.main.splash_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class SplashFragment : BaseFragment<SplashViewEvent, SplashViewModelState, SplashViewModelState>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerSplashComponent
            .builder()
            .appComponent(InjectionsHolder.appComponent)
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logo.animate().setInterpolator(OvershootInterpolator())
            .setDuration(400)
            .scaleX(1f)
            .scaleY(1f)
            .alpha(1f)
            .setStartDelay(400)
            .start()
    }

    override fun viewEvents() = emptyFlow<SplashViewEvent>()

    override fun render(state: SplashViewModelState) {
        if (state is SplashViewModelState.Loading) {
            logo.animate()
                .rotation(360f)
                .setDuration(800)
                .start()
        }
    }

    companion object {
        fun newInstance() = SplashFragment()
    }
}
