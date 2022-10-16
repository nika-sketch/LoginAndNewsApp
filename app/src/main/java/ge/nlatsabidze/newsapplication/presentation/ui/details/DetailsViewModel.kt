package ge.nlatsabidze.newsapplication.presentation.ui.details

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.data.model.ArticleUi

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val sharedDetails: ArticleDetails.Mutable<ArticleUi>
) : ViewModel(), ArticleDetails.Read<ArticleUi> {

    override fun read() = sharedDetails.read()
}