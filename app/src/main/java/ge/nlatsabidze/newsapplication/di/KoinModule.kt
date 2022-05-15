package ge.nlatsabidze.newsapplication.di

import android.content.Context
import android.content.res.Resources
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.data.model.News
import ge.nlatsabidze.newsapplication.data.remote.NewsApi
import ge.nlatsabidze.newsapplication.data.repository.NewsRepositoryImpl
import ge.nlatsabidze.newsapplication.domain.repository.NewsRepository
import ge.nlatsabidze.newsapplication.domain.repository.ResponseHandler
import ge.nlatsabidze.newsapplication.domain.usecases.NewsUseCase
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val viewModelModule = module {
    viewModel {
        NewsViewModel(get(), get(), get(), get())
    }
}

val provideRetrofit = module {

    fun provideRetrofitCurrency(retrofitClient: Retrofit): NewsApi =
        retrofitClient.create(NewsApi::class.java)

    single { provideRetrofitCurrency(get()) }

    single<Retrofit> {
        Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
            )
        ).build()
    }
}


val provideResponse = module {
    fun provideResponseHandler(provideInternetConnectionChecker: ProvideInternetConnectionChecker): ResponseHandler =
        ResponseHandler.Base(provideInternetConnectionChecker)

    fun provideInternetConnection(context: Context): ProvideInternetConnectionChecker =
        ProvideInternetConnectionChecker.NetworkHelper(context)

    single { provideResponseHandler(get()) }
    single { provideInternetConnection(androidContext()) }
}

val provideOther = module {
    fun provideDispatchers(): MyDispatchers = MyDispatchers.Base()


    fun provideCurrencyRepository(api: NewsApi, responseHandler: ResponseHandler): NewsRepository =
        NewsRepositoryImpl(api, responseHandler)

    fun provideUseCase(newsRepository: NewsRepository): NewsUseCase =
        NewsUseCase.GetNewsUseCase(newsRepository)


    fun provideCommunication(): Communication<Resource<News>> = Communication.Base(Resource.EmptyData())

    single { provideDispatchers() }
    single { provideCurrencyRepository(get(), get()) }
    single { provideUseCase(get()) }
    single { provideCommunication() }
}





