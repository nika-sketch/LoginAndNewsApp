package ge.nlatsabidze.newsapplication.di


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.*
import dagger.hilt.*
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.data.remote.NewsApi
import ge.nlatsabidze.newsapplication.data.repository.NewsRepository
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepositoryImpl
import ge.nlatsabidze.newsapplication.domain.repository.ResponseHandler
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitCurrency(): NewsApi =
        Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(NewsApi::class.java)


    @Provides
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler.Base()
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(api: NewsApi, responseHandler: ResponseHandler): NewsRepository {
        return NewsRepositoryImpl(api, responseHandler)
    }
}