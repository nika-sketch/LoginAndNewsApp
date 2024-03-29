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
import ge.nlatsabidze.newsapplication.data.cache.*
import ge.nlatsabidze.newsapplication.data.remote.NewsService
import ge.nlatsabidze.newsapplication.data.repository.BaseNewsServiceRepository
import ge.nlatsabidze.newsapplication.domain.cache.ArticleDao
import ge.nlatsabidze.newsapplication.domain.cache.ArticleRepository
import ge.nlatsabidze.newsapplication.domain.interactor.NewsInteractor
import ge.nlatsabidze.newsapplication.domain.repository.NewsServiceRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named
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
        handleService: HandleService
    ): NewsServiceRepository = BaseNewsServiceRepository(handleService)

    @Provides
    fun provideInteractorNews(
        repository: NewsServiceRepository,
        backgroundCoroutine: CoroutineDispatcher
    ): NewsInteractor = NewsInteractor.Base(repository, backgroundCoroutine)

    @Provides
    fun provideHandleArticlesModule(articleRepository: ArticleRepository): HandleArticles =
        HandleArticles.Base(articleRepository)

    @Provides
    fun provideService(
        error: Error,
        handleException: HandleException,
        handleArticles: HandleArticles,
        @Named("success") handleSuccess: HandleService
    ): HandleService =
        HandleService.Base(
            error,
            handleException,
            handleArticles,
            handleSuccess
        )

    @Provides
    @Named("success")
    fun provideSuccessResult(
        newsService: NewsService,
        articleRepository: ArticleRepository,
    ): HandleService = SuccessResult(newsService, articleRepository)

}
