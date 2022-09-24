package ge.nlatsabidze.newsapplication.domain.interactor

import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.CoroutineDispatcher
import ge.nlatsabidze.newsapplication.data.repository.NewsResult
import ge.nlatsabidze.newsapplication.domain.repository.NewsServiceRepository

interface NewsInteractor {

    fun execute(): Flow<NewsResult>

    class Base @Inject constructor(
        private val repository: NewsServiceRepository,
        private val backgroundCoroutine: CoroutineDispatcher
    ) : NewsInteractor {
        override fun execute(): Flow<NewsResult> = flow {
            emit(repository.fetchNews())
        }.onStart {
            emit(NewsResult.LoadingResult)
        }.flowOn(backgroundCoroutine).catch {
            emit(NewsResult.ErrorResult(it.message!!))
        }
    }
}