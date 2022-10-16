package ge.nlatsabidze.newsapplication.data.model

import ge.nlatsabidze.newsapplication.domain.model.NewsDomain

data class NewsResponse(
    private val articles: MutableList<Article>?,
    private val status: String?,
    private val totalResults: Int?
) {

    interface Mapper<T> {
        fun map(articles: MutableList<Article>?): T
    }

    fun <T> map(mapper: Mapper<T>): T = mapper.map(articles)
}

class NewsResponseToNewDomain : NewsResponse.Mapper<NewsDomain> {
    override fun map(articles: MutableList<Article>?): NewsDomain {
        return NewsDomain(articles ?: mutableListOf())
    }
}
