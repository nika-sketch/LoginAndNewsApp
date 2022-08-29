package ge.nlatsabidze.newsapplication.domain.interactor

import ge.nlatsabidze.newsapplication.core.Result
import ge.nlatsabidze.newsapplication.data.repository.NewsResult
import ge.nlatsabidze.newsapplication.domain.model.NewsDomain
import ge.nlatsabidze.newsapplication.domain.repository.NewsServiceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * old version
 */
interface NewsInteractor {

    fun execute(): Flow<Result<NewsDomain>>
}


interface InteractorNews {

    fun execute(): Flow<NewsResult>

    class Base @Inject constructor(
        private val repository: NewsServiceRepository,
        private val backgroundCoroutine: CoroutineDispatcher
    ) : InteractorNews {
        override fun execute(): Flow<NewsResult> = flow {
            emit(repository.fetchNews())
        }.onStart {
            emit(NewsResult.LoadingResult)
        }.flowOn(backgroundCoroutine).catch {
            emit(NewsResult.ErrorResult(it.message!!))
        }
    }
}