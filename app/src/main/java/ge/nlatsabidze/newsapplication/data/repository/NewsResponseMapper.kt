package ge.nlatsabidze.newsapplication.data.repository

import ge.nlatsabidze.newsapplication.core.Mapper
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.domain.interactor.NewsDomain

class NewsResponseMapper : Mapper<NewsResponse, NewsDomain> {
    override fun map(source: NewsResponse): NewsDomain {
        return NewsDomain(source.articles!!, source.status!!)
    }
}
