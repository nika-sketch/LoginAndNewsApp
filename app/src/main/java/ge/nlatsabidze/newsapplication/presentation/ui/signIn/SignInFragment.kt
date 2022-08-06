package ge.nlatsabidze.newsapplication.presentation.ui.signIn

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.core.onTap
import ge.nlatsabidze.newsapplication.databinding.SignInFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment

@AndroidEntryPoint
class SignInFragment : BaseFragment<SignInFragmentBinding>(SignInFragmentBinding::inflate) {

    private val viewModel by viewModels<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        button.onTap { viewModel.signIn(email.text.toString(), password.text.toString()) }
        viewModel.collect(viewLifecycleOwner) { it.apply(binding) }
    }
}
