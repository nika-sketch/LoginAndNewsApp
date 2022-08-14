package ge.nlatsabidze.newsapplication.domain.interactor

import ge.nlatsabidze.newsapplication.core.Result
import ge.nlatsabidze.newsapplication.domain.model.NewsDomain
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

interface NewsInteractor {

    fun execute(): Flow<Result<NewsDomain>>

    class GetNewsUseCase @Inject constructor(
        @Named("currencyRepository") private val newsRepository: NewsRepository,
        private val backgroundCoroutine: CoroutineDispatcher
    ) : NewsInteractor {
        override fun execute(): Flow<Result<NewsDomain>> = flow {
            emit(newsRepository.fetchNews())
        }.onStart { emit(Result.Loading()) }.flowOn(backgroundCoroutine).catch {
            emit(Result.Error(it.message.toString()))
        }
    }
}
