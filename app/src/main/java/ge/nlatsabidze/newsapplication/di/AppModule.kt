package ge.nlatsabidze.newsapplication.di


import dagger.Module
import dagger.Provides
import javax.inject.Named
import dagger.hilt.InstallIn
import android.content.Context
import ge.nlatsabidze.newsapplication.core.*
import kotlinx.coroutines.CoroutineDispatcher
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.data.remote.NewsService
import ge.nlatsabidze.newsapplication.domain.model.NewsDomain
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import ge.nlatsabidze.newsapplication.domain.repository.HandleResponse
import ge.nlatsabidze.newsapplication.domain.interactor.NewsInteractor
import ge.nlatsabidze.newsapplication.data.repository.NewsRepositoryImpl
import ge.nlatsabidze.newsapplication.data.repository.NewsResponseMapper
import ge.nlatsabidze.newsapplication.data.interactor.NewsInteractorImpl

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUseCase(
        @Named("currencyRepository") newsRepository: NewsRepository,
        coroutineDispatcher: CoroutineDispatcher,
    ): NewsInteractor = NewsInteractorImpl(newsRepository, coroutineDispatcher)

    @Provides
    @Named("currencyRepository")
    fun provideCurrencyRepository(
        api: NewsService,
        repoMapper: Mapper<NewsResponse, NewsDomain>,
        responseHandler: HandleResponse
    ): NewsRepository =
        NewsRepositoryImpl(api, repoMapper, responseHandler)

    @Provides
    fun provideRepoMapper(): Mapper<NewsResponse, NewsDomain> = NewsResponseMapper()

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

    @Provides
    fun provideObserveConnectivity(@ApplicationContext context: Context): ObserveConnectivity =
        ObserveConnectivity.Base(context)

    @Provides
    fun provideObserveConnectivityChannel(): Communication<ObserveConnectivity.Status> =
        Communication.ObserveConnection()
}
