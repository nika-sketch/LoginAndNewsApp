package ge.nlatsabidze.newsapplication.di


import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.common.Communication
import ge.nlatsabidze.newsapplication.common.Constants.BASE_URL
import ge.nlatsabidze.newsapplication.common.MyDispatchers
import ge.nlatsabidze.newsapplication.common.InternetConnection
import ge.nlatsabidze.newsapplication.common.Resource
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.data.remote.NewsApi
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import ge.nlatsabidze.newsapplication.data.repository.NewsRepositoryImpl
import ge.nlatsabidze.newsapplication.domain.repository.ResponseHandler
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            )
        ).build()


    @Provides
    @Singleton
    fun provideRetrofitCurrency(retrofitClient: Retrofit): NewsApi =
        retrofitClient.create(NewsApi::class.java)


    @Provides
    fun provideResponseHandler(provideInternetConnectionChecker: InternetConnection): ResponseHandler =
        ResponseHandler.Base(provideInternetConnectionChecker)


    @Provides
    fun provideDispatchers(): MyDispatchers = MyDispatchers.Base()


    @Provides
    fun provideCurrencyRepository(api: NewsApi, responseHandler: ResponseHandler): NewsRepository =
        NewsRepositoryImpl(api, responseHandler)

    @Provides
    fun provideUseCase(
        newsRepository: NewsRepository,
        coroutineDispatcher: CoroutineDispatcher
    ): NewsUseCase =
        NewsUseCase.GetNewsUseCase(newsRepository, coroutineDispatcher)

    @Provides
    fun provideIo(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideCommunication(): Communication<NewsUi> =
        Communication.Base(NewsUi.LoadingUi())

    @Provides
    fun provideInternetConnection(@ApplicationContext context: Context): InternetConnection =
        InternetConnection.NetworkHelper(context)

}