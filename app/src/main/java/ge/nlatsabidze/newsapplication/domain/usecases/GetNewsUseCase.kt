package ge.nlatsabidze.newsapplication.domain.usecases

import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<News>> = flow {
        try {
            emit(Resource.Loading())
            emit(newsRepository.getNews())
        } catch (e: Exception) {
            emit(Resource.EmptyData())
        }
    }
}