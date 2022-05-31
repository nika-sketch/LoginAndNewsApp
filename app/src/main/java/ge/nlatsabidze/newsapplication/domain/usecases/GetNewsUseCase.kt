package ge.nlatsabidze.newsapplication.domain.usecases

import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface NewsUseCase {

    operator fun invoke(): Flow<Resource<News>>

    class GetNewsUseCase @Inject constructor(
        private val newsRepository: NewsRepository,
        private val IO: CoroutineDispatcher,
    ) : NewsUseCase {
        override operator fun invoke(): Flow<Resource<News>> = flow {
            emit(newsRepository.getNews())
        }.onStart { emit(Resource.Loading()) }.flowOn(IO)
            .catch {
                emit(Resource.Error(it.message.toString()))
            }
    }
}
