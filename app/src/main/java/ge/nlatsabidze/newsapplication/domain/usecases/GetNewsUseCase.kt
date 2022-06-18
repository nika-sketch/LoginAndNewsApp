package ge.nlatsabidze.newsapplication.domain.usecases

import ge.nlatsabidze.newsapplication.common.Result
import ge.nlatsabidze.newsapplication.data.model.MyNews
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface NewsUseCase {

    fun execute(): Flow<Result<MyNews>>

    class GetNewsUseCase @Inject constructor(
        private val newsRepository: NewsRepository,
        private val backgroundCoroutine: CoroutineDispatcher
    ) : NewsUseCase {
        override fun execute(): Flow<Result<MyNews>> = flow {
            emit(newsRepository.fetchNews())
        }.onStart { emit(Result.Loading()) }.flowOn(backgroundCoroutine).catch {
            emit(Result.Error(it.message.toString()))
        }
    }
}

