package ge.nlatsabidze.newsapplication.domain.usecases

import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface NewsUseCase {

    operator fun invoke(): Flow<Resource<News>>

    class GetNewsUseCase @Inject constructor(
        private val newsRepository: NewsRepository
    ): NewsUseCase {
        override operator fun invoke(): Flow<Resource<News>> = flow {
            try {
                emit(Resource.Loading())
                emit(newsRepository.getNews())
            } catch (e: Exception) {
                emit(Resource.EmptyData())
            }
        }
    }
}
