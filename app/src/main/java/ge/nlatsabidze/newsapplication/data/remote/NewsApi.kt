package ge.nlatsabidze.newsapplication.data.remote

import ge.nlatsabidze.newsapplication.data.model.News
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("everything?domains=wsj.com&apiKey=da763a2a9c9a4ce8a636e4c1fac676a2")
    suspend fun getMarketItems(): Response<News>
}