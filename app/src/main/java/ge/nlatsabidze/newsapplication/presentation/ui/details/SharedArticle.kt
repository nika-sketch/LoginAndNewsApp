package ge.nlatsabidze.newsapplication.presentation.ui.details

import androidx.lifecycle.LifecycleOwner
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject
import javax.inject.Singleton

interface SharedArticle {

    suspend fun save(
        articleUi: ArticleUi
    )

    suspend fun collectDetailArticle(
        viewLifecycleOwner: LifecycleOwner,
        flowCollector: FlowCollector<ArticleDetailsUi>
    )

    @Singleton
    class Base @Inject constructor(
        private val articleState: Communication<ArticleDetailsUi>
    ) : SharedArticle {

        override suspend fun save(articleUi: ArticleUi) =
            articleState.map(ArticleDetailsUi.Saved(articleUi))

        override suspend fun collectDetailArticle(
            viewLifecycleOwner: LifecycleOwner,
            flowCollector: FlowCollector<ArticleDetailsUi>
        ) = articleState.collect(viewLifecycleOwner, flowCollector)
    }
}