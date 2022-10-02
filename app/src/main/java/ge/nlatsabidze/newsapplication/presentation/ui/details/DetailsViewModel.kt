package ge.nlatsabidze.newsapplication.presentation.ui.details

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val sharedDetails: ArticleDetails.Mutable
) : ViewModel(), ArticleDetails.Read {

    override fun read() = sharedDetails.read()
}