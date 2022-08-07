package ge.nlatsabidze.newsapplication.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.core.*
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.data.remote.NewsApi
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import ge.nlatsabidze.newsapplication.data.repository.NewsRepositoryImpl
import ge.nlatsabidze.newsapplication.data.repository.NewsResponseMapper
import ge.nlatsabidze.newsapplication.domain.interactor.MyNews
import ge.nlatsabidze.newsapplication.domain.repository.HandleResponse
import ge.nlatsabidze.newsapplication.domain.interactor.NewsInteractor
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUseCase(
        @Named("currencyRepository") newsRepository: NewsRepository,
        coroutineDispatcher: CoroutineDispatcher,
    ): NewsInteractor =
        NewsInteractor.GetNewsUseCase(newsRepository, coroutineDispatcher)

    @Provides
    @Named("currencyRepository")
    fun provideCurrencyRepository(
        api: NewsApi,
        repoMapper: Mapper<NewsResponse, MyNews>,
        responseHandler: HandleResponse
    ): NewsRepository =
        NewsRepositoryImpl(api, repoMapper, responseHandler)

    @Provides
    fun provideRepoMapper(): Mapper<NewsResponse, MyNews> = NewsResponseMapper()

    @Provides
    fun provideResponseHandler(
        internetConnection: InternetConnection,
        handleResult: HandleResult,
        errorProvide: Error,
        handleException: HandleException
    ): HandleResponse =
        HandleResponse.Base(internetConnection, handleResult, errorProvide, handleException)

    @Provides
    fun provideError(resources: ProvideResources): Error = Error.NoConnection(resources)

    @Provides
    fun provideException(): HandleException = HandleException.Base()

}
