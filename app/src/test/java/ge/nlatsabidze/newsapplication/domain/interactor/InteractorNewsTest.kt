package ge.nlatsabidze.newsapplication.domain.interactor

import ge.nlatsabidze.newsapplication.data.repository.NewsResult
import ge.nlatsabidze.newsapplication.domain.repository.NewsServiceRepository
import junit.framework.TestCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking
import org.junit.Test

class InteractorNewsTest : TestCase() {

    private class FakeServiceRepository : NewsServiceRepository {
        override suspend fun fetchNews(): NewsResult = NewsResult.LoadingResult
    }

    private val fakeCoroutineDispatcher = Dispatchers.IO

    @Test
    fun testNewsInteractor() {

        val fakeNewsServiceRepository = FakeServiceRepository()
        val newsInteractor = InteractorNews.Base(fakeNewsServiceRepository, fakeCoroutineDispatcher)
        val actual = newsInteractor.execute()

        runBlocking {
            testFlow().collect {
                assertEquals(it, fakeNewsServiceRepository.fetchNews())
            }
        }
    }

    private fun testFlow(): Flow<NewsResult> = flow {
        val fakeNewsServiceRepository = FakeServiceRepository()
        emit(fakeNewsServiceRepository.fetchNews())
    }.catch { NewsResult.ErrorResult(it.message!!) }
}