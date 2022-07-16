package ge.nlatsabidze.newsapplication.presentation.ui.details

import javax.inject.Named
import javax.inject.Inject
import ge.nlatsabidze.newsapplication.core.Mapper
import ge.nlatsabidze.newsapplication.core.LoadImage
import ge.nlatsabidze.newsapplication.core.containsBraces
import ge.nlatsabidze.newsapplication.core.firstIndexOfOpenBrace
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding

interface Details {

    fun showDetails(binding: DetailsFragmentBinding, argsArticle: Article)

    class Base @Inject constructor(
        private val imageLoader: LoadImage,
        @Named("firstItem") val dateFormatter: Mapper<String, String>
    ) : Details {
        override fun showDetails(binding: DetailsFragmentBinding, argsArticle: Article) =
            with(binding) {
                imageLoader.load(binding.contentImage, argsArticle.urlToImage)
                date.text = argsArticle.publishedAt
                personName.text = argsArticle.author
                journalName.text = argsArticle.source!!.name
                date.text = argsArticle.publishedAt?.let { dateFormatter.map(it) }
                if (argsArticle.content!!.containsBraces()) binding.newsContent.text =
                    argsArticle.content.substring(
                        0,
                        argsArticle.content.firstIndexOfOpenBrace()
                    )
                else newsContent.text = argsArticle.content
                newsTitle.text = argsArticle.title
            }
    }
}
