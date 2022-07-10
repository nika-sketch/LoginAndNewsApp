package ge.nlatsabidze.newsapplication.presentation.ui.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsFragmentBinding>(DetailsFragmentBinding::inflate) {

    private val argsArticle: DetailsFragmentArgs by navArgs()

    @Inject lateinit var details: Details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
        details.showDetails(binding, argsArticle)
}
