package ge.nlatsabidze.newsapplication.data.model

import ge.nlatsabidze.newsapplication.core.Mapper

class ArticleMapper : Mapper<MutableList<Article>, MutableList<ArticleUi>> {

    private fun mapArticle(source: Article): ArticleUi = ArticleUi(
        author = source.author ?: "author is not provided",
        content = source.content ?: "content is not provided",
        description = source.description ?: "description is not provided",
        publishedAt = source.publishedAt ?: "date is not provided",
        source = source.source ?: Source("", ""),
        title = source.title ?: "title not provided",
        url = source.url ?: "",
        urlToImage = source.urlToImage ?: "https://reqres.in/img/faces/8-image.jpg"
    )

    override fun map(source: MutableList<Article>): MutableList<ArticleUi> =
        source.map { mapArticle(it) }.toMutableList()
}
