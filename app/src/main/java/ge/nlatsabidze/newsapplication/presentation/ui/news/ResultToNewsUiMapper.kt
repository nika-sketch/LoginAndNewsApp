package ge.nlatsabidze.newsapplication.presentation.ui.news

import ge.nlatsabidze.newsapplication.common.Communication
import ge.nlatsabidze.newsapplication.common.Result
import ge.nlatsabidze.newsapplication.data.model.MyNews
import kotlinx.coroutines.flow.Flow


interface ResultToNewsUiMapper {

    suspend fun toNewsUi(flow: Flow<Result<MyNews>>, communication: Communication<NewsUi>)

    class Base : ResultToNewsUiMapper {
        override suspend fun toNewsUi(
            flow: Flow<Result<MyNews>>,
            communication: Communication<NewsUi>
        ) {
            flow.collect {
                val newsUi = when (it) {
                    is Result.Loading -> NewsUi.Loading()
                    is Result.Success -> NewsUi.Success(it.data.articles)
                    is Result.Error -> NewsUi.Error(it.message)
                }
                communication.map(newsUi)
            }
        }
    }
}