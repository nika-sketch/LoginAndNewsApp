package ge.nlatsabidze.newsapplication.domain.interactor

import android.os.Parcelable
import ge.nlatsabidze.newsapplication.core.Result
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

interface NewsInteractor {

    fun execute(): Flow<Result<MyNews>>

    class GetNewsUseCase @Inject constructor(
        @Named("currencyRepository") private val newsRepository: NewsRepository,
        private val backgroundCoroutine: CoroutineDispatcher
    ) : NewsInteractor {
        override fun execute(): Flow<Result<MyNews>> = flow {
            emit(newsRepository.fetchNews())
        }.onStart { emit(Result.Loading()) }.flowOn(backgroundCoroutine).catch {
            emit(Result.Error(it.message.toString()))
        }
    }
}

@Parcelize
data class MyNews(
    val articles: MutableList<Article>,
    val status: String,
): Parcelable
