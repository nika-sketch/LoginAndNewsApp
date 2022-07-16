package ge.nlatsabidze.newsapplication.presentation.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.core.collectFlow
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsFragmentBinding>(DetailsFragmentBinding::inflate) {

    private val viewModel: NewsViewModel by activityViewModels()

    @Inject lateinit var details: Details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.collectArticle(viewLifecycleOwner) {
            details.showDetails(binding, it!!)
        }
    }
}
