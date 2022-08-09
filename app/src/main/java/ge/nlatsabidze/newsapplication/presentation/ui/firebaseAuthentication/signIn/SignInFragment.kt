package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.signIn

import android.os.Bundle
import android.view.View
import javax.inject.Inject
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.core.onTap
import androidx.navigation.fragment.findNavController
import ge.nlatsabidze.newsapplication.R
import ge.nlatsabidze.newsapplication.presentation.ui.core.Text
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.databinding.SignInFragmentBinding

@AndroidEntryPoint
class SignInFragment : BaseFragment<SignInFragmentBinding>(SignInFragmentBinding::inflate) {

    private val viewModel by viewModels<SignInViewModel>()
    @Inject lateinit var text: Text

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        button.onTap { viewModel.signIn(text.text(email), text.text(password)) }
        viewModel.collectFirebaseAuth(viewLifecycleOwner) { it.apply(signInGlobal, findNavController()) }
        viewModel.collectVisibility(viewLifecycleOwner) { it.apply(progressBar) }

        binding.tvSignUp.onTap {
            findNavController().navigate(R.id.action_signInFragment_to_registerFragment)
        }
    }
}
