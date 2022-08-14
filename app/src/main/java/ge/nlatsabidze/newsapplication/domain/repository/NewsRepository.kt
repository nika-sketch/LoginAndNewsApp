package ge.nlatsabidze.newsapplication.domain.repository

import ge.nlatsabidze.newsapplication.core.Result
import ge.nlatsabidze.newsapplication.domain.model.NewsDomain

interface NewsRepository  {

    suspend fun fetchNews(): Result<NewsDomain>
}
