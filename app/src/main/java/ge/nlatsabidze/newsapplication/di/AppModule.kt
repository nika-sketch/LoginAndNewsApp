package ge.nlatsabidze.newsapplication.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.data.remote.NewsApi
import ge.nlatsabidze.newsapplication.data.repository.BaseResponseHandler
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import ge.nlatsabidze.newsapplication.data.repository.NewsRepositoryImpl
import ge.nlatsabidze.newsapplication.data.repository.NewsResponseMapper
//import ge.nlatsabidze.newsapplication.domain.repository.ResponseHandler
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    fun provideResponseHandler(
//        provideInternetConnectionChecker: InternetConnection,
//        resourceManager: ResourceManager
//    ): ResponseHandler =
//        ResponseHandler.Base(provideInternetConnectionChecker, resourceManager)

    @Provides
    fun provideUseCase(
        newsRepository: NewsRepository,
        coroutineDispatcher: CoroutineDispatcher,
    ): NewsUseCase =
        NewsUseCase.GetNewsUseCase(newsRepository, coroutineDispatcher)

    @Provides
    fun provideCurrencyRepository(
        api: NewsApi,
        repoMapper: NewsResponseMapper,
        internetConnection: InternetConnection,
        baseResponseHandler: BaseResponseHandler
    ): NewsRepository =
        NewsRepositoryImpl(api, repoMapper, baseResponseHandler)

    @Provides
    fun provideRepoMapper(): NewsResponseMapper = NewsResponseMapper()

    @Provides
    fun provideResponseHandler(
        internetConnection: InternetConnection,
        resourceManager: ResourceManager
    ): BaseResponseHandler {
        return BaseResponseHandler(internetConnection, resourceManager)
    }

}