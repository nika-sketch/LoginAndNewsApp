package ge.nlatsabidze.newsapplication.data.remote

import ge.nlatsabidze.newsapplication.common.Constants.GET_NEWS
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET(GET_NEWS)
    suspend fun getMarketItems(): Response<NewsResponse>
}