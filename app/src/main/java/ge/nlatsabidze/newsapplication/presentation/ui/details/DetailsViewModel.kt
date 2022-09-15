package ge.nlatsabidze.newsapplication.presentation.ui.details

import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.FlowCollector
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.nlatsabidze.newsapplication.core.launchMain

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val sharedArticle: SharedArticle
) : ViewModel() {

    fun collectDetailArticle(
        lifecycleOwner: LifecycleOwner,
        collector: FlowCollector<ArticleDetailsUi>
    ) = launchMain {
        sharedArticle.collectDetailArticle(lifecycleOwner, collector)
    }
}