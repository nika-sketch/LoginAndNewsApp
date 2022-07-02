package ge.nlatsabidze.newsapplication.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Provides
    fun provideResourceManager(@ApplicationContext context: Context): ProvideResources =
        ProvideResources.Base(context)

    @Provides
    fun provideInternetConnection(@ApplicationContext context: Context): InternetConnection =
        InternetConnection.NetworkHelper(context)

    @Provides
    fun provideCommunication(): Communication<NewsUi> =
        Communication.BaseNews(NewsUi.Loading())

    @Provides
    fun provideIo(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideDispatchers(): MyDispatchers = MyDispatchers.Base()

    @Provides
    fun provideResultHandler(): HandleResult = HandleResult.Base()

    @Provides
    @Named("firstItem")
    fun provideImageLoader(): LoadImage = LoadImage.GithubImageBase()

    @Provides
    @Named("stringMapper")
    fun provideDateFormat(): Mapper<String, String> = AbstractDateFormat.DateFormatter()
}
