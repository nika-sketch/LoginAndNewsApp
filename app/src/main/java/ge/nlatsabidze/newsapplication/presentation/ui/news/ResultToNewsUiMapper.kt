package ge.nlatsabidze.newsapplication.presentation.ui.news

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import ge.nlatsabidze.newsapplication.core.Result
import ge.nlatsabidze.newsapplication.core.ResultMapper
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.domain.interactor.NewsDomain

interface ResultToNewsUiMapper {

    suspend fun toNewsUi(flow: Flow<Result<NewsDomain>>, communication: Communication<NewsUi>)

    class Base @Inject constructor(
        private val mapper: ResultMapper<NewsUi, NewsDomain>
    ) :
        ResultToNewsUiMapper {
        override suspend fun toNewsUi(
            flow: Flow<Result<NewsDomain>>,
            communication: Communication<NewsUi>
        ) {
            flow.collect { result ->
                communication.map(result.map(mapper))
            }
        }
    }
}
