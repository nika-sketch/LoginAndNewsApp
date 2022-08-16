package ge.nlatsabidze.newsapplication.data.interactor

import javax.inject.Named
import javax.inject.Inject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.CoroutineDispatcher
import ge.nlatsabidze.newsapplication.core.Result
import ge.nlatsabidze.newsapplication.domain.model.NewsDomain
import ge.nlatsabidze.newsapplication.domain.interactor.NewsInteractor
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository

class NewsInteractorImpl @Inject constructor(
    @Named("currencyRepository") private val newsRepository: NewsRepository,
    private val backgroundCoroutine: CoroutineDispatcher
) : NewsInteractor {
    override fun execute(): Flow<Result<NewsDomain>> = flow {
        emit(newsRepository.fetchNews())
    }.onStart { emit(Result.Loading()) }.flowOn(backgroundCoroutine).catch {
        emit(Result.Error(it.message.toString()))
    }
}
