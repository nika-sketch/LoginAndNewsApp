package ge.nlatsabidze.newsapplication.domain.repository

import ge.nlatsabidze.newsapplication.core.Result
import ge.nlatsabidze.newsapplication.data.model.MyNews

interface NewsRepository  {

    suspend fun fetchNews(): Result<MyNews>
}
