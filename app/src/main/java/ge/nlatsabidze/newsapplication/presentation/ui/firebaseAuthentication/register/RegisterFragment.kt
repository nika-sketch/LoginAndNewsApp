package ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.databinding.RegisterFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.presentation.ui.core.Text
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterFragmentBinding>(RegisterFragmentBinding::inflate) {

    private val viewModel by viewModels<RegisterViewModel>()
    @Inject lateinit var text: Text

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        btnRegister.setOnClickListener { viewModel.register(text.text(etName), text.text(email), text.text(password)) }
        viewModel.collectEvent(viewLifecycleOwner) { it.apply(registerGlobal,this@RegisterFragment) }
        viewModel.collectState(viewLifecycleOwner) { it.apply(progressBar) }
    }
}