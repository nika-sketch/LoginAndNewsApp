package ge.nlatsabidze.newsapplication.domain.usecases

import ge.nlatsabidze.newsapplication.common.Mapper
import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.MyNews
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface NewsUseCase {

    fun execute(): Flow<Resource<MyNews>>

    class GetNewsUseCase @Inject constructor(
        private val newsRepository: NewsRepository,
        private val backgroundCoroutine: CoroutineDispatcher
    ) : NewsUseCase {
        override fun execute(): Flow<Resource<MyNews>> = flow {
            emit(newsRepository.fetchNews())
        }.onStart { emit(Resource.Loading()) }.flowOn(backgroundCoroutine)
            .catch {
                emit(Resource.Error(it.message.toString()))
            }
    }
}

