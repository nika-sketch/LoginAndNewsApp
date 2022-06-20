package ge.nlatsabidze.newsapplication.di


import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.data.model.MyNews
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.data.remote.NewsApi
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import ge.nlatsabidze.newsapplication.data.repository.NewsRepositoryImpl
import ge.nlatsabidze.newsapplication.data.repository.NewsResponseMapper
import ge.nlatsabidze.newsapplication.domain.repository.ResponseHandler
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUseCase(
        @Named("currencyRepository") newsRepository: NewsRepository,
        coroutineDispatcher: CoroutineDispatcher,
    ): NewsUseCase =
        NewsUseCase.GetNewsUseCase(newsRepository, coroutineDispatcher)

    @Provides
    @Named("currencyRepository")
    fun provideCurrencyRepository(
        api: NewsApi,
        repoMapper: Mapper<NewsResponse, MyNews>,
        responseHandler: ResponseHandler
    ): NewsRepository =
        NewsRepositoryImpl(api, repoMapper, responseHandler)

    @Provides
    fun provideRepoMapper(): Mapper<NewsResponse, MyNews> = NewsResponseMapper()

    @Provides
    fun provideResponseHandler(
        internetConnection: InternetConnection,
        resourceManager: ResourceManager,
        handleResult: HandleResult,
    ): ResponseHandler = ResponseHandler.Base(internetConnection, resourceManager, handleResult)
}
