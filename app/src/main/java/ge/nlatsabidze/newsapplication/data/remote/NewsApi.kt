package ge.nlatsabidze.newsapplication.data.remote

import ge.nlatsabidze.newsapplication.data.model.News
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("everything?q=Wall%20Street%20Journal&from=2022-03-19&sortBy=publishedAt&apiKey=da763a2a9c9a4ce8a636e4c1fac676a2")
    suspend fun getMarketItems(): Response<News>
}