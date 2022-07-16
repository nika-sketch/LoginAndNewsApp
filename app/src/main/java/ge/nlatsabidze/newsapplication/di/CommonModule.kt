package ge.nlatsabidze.newsapplication.di

import dagger.Module
import dagger.Provides
import javax.inject.Named
import dagger.hilt.InstallIn
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineDispatcher
import ge.nlatsabidze.newsapplication.core.*
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.data.model.MyNews
import ge.nlatsabidze.newsapplication.presentation.ui.news.NewsUi
import ge.nlatsabidze.newsapplication.presentation.ui.details.Details
import ge.nlatsabidze.newsapplication.presentation.ui.news.Navigation
import ge.nlatsabidze.newsapplication.presentation.ui.news.ResultToNewsUiMapper

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
    fun provideArticleCommunication(): Communication<Article?> =
        Communication.BaseArticle()

    @Provides
    fun provideIo(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideDispatchers(): ge.nlatsabidze.newsapplication.core.Dispatchers =
        ge.nlatsabidze.newsapplication.core.Dispatchers.Base()

    @Provides
    fun provideResultHandler(): HandleResult = HandleResult.Base()

    @Provides
    @Named("firstItem")
    fun provideImageLoader(): LoadImage = LoadImage.GithubImageBase()

    @Provides
    @Named("stringMapper")
    fun provideDateFormat(): Mapper<String, String> = AbstractDateFormat.DateFormatter()

    @Provides
    fun provideDetails(
        @Named("firstItem") imageLoader: LoadImage,
        @Named("stringMapper") mapper: Mapper<String, String>
    ): Details = Details.Base(imageLoader, mapper)

    @Provides
    fun provideResultFactory(mapper: ResultMapper<NewsUi, MyNews>): ResultToNewsUiMapper =
        ResultToNewsUiMapper.Base(mapper)

    @Provides
    fun providesResultMapper(): ResultMapper<NewsUi, MyNews> = ResultMapper.ToNewsUi()

    @Provides
    fun provideChannel(): Communication<Navigation> = Communication.BaseChannel()
}
