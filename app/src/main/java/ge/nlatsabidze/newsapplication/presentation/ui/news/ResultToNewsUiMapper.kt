package ge.nlatsabidze.newsapplication.presentation.ui.news

import ge.nlatsabidze.newsapplication.common.Communication
import ge.nlatsabidze.newsapplication.common.Result
import ge.nlatsabidze.newsapplication.common.ResultMapper
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.data.model.MyNews
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface ResultToNewsUiMapper {

    suspend fun toNewsUi(flow: Flow<Result<MyNews>>, communication: Communication<NewsUi>)

    class Base @Inject constructor(
        private val mapper: ResultMapper<NewsUi, MyNews>
    ) :
        ResultToNewsUiMapper {
        override suspend fun toNewsUi(
            flow: Flow<Result<MyNews>>,
            communication: Communication<NewsUi>
        ) {
            flow.collect { result ->
                communication.map(result.map(mapper))
            }
        }
    }
}