package ge.nlatsabidze.newsapplication.data.repository

import ge.nlatsabidze.newsapplication.core.Mapper
import ge.nlatsabidze.newsapplication.data.model.NewsResponse
import ge.nlatsabidze.newsapplication.domain.interactor.MyNews

class NewsResponseMapper : Mapper<NewsResponse, MyNews> {
    override fun map(source: NewsResponse): MyNews {
        return MyNews(source.articles!!, source.status!!)
    }
}
