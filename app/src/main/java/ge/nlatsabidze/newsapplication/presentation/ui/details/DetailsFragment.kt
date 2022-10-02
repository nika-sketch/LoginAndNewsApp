package ge.nlatsabidze.newsapplication.presentation.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsFragmentBinding>(DetailsFragmentBinding::inflate) {

    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val details = viewModel.read()
        details.bindDetails(binding)
    }
}
