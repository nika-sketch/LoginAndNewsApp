package ge.nlatsabidze.newsapplication.di

import android.content.Context
import androidx.room.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.core.Error
import ge.nlatsabidze.newsapplication.core.HandleException
import ge.nlatsabidze.newsapplication.core.Mapper
import ge.nlatsabidze.newsapplication.data.cache.ArticleDataBase
import ge.nlatsabidze.newsapplication.data.cache.ArticleRepositoryImpl
import ge.nlatsabidze.newsapplication.data.cache.HandleService
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.data.remote.NewsService
import ge.nlatsabidze.newsapplication.data.repository.NewsServiceRepositoryImpl
import ge.nlatsabidze.newsapplication.domain.cache.ArticleDao
import ge.nlatsabidze.newsapplication.domain.cache.ArticleRepository
import ge.nlatsabidze.newsapplication.domain.interactor.InteractorNews
import ge.nlatsabidze.newsapplication.domain.model.NewsDomain
import ge.nlatsabidze.newsapplication.domain.repository.NewsServiceRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        ArticleDataBase::class.java,
        "db_name"
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(db: ArticleDataBase): ArticleDao = db.articleDao()


    @Provides
    @Singleton
    fun provideArticleRepository(api: ArticleDao): ArticleRepository = ArticleRepositoryImpl(api)

    @Provides
    fun provideRepo(
        articleRepository: ArticleRepository,
        handleService: HandleService
    ): NewsServiceRepository = NewsServiceRepositoryImpl(articleRepository, handleService)

    @Provides
    fun provideInteractorNews(
        repository: NewsServiceRepository,
        backgroundCoroutine: CoroutineDispatcher
    ): InteractorNews = InteractorNews.Base(repository, backgroundCoroutine)

    @Provides
    fun provideService(
        newsService: NewsService,
        mapper: Mapper<NewsResponse, NewsDomain>,
        error: Error,
        handleException: HandleException,
        ArticleRepository: ArticleRepository
    ): HandleService =
        HandleService.Base(newsService, mapper, error, ArticleRepository, handleException)
}
