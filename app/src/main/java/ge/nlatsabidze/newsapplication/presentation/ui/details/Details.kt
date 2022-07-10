package ge.nlatsabidze.newsapplication.presentation.ui.details

import javax.inject.Named
import javax.inject.Inject
import ge.nlatsabidze.newsapplication.common.Mapper
import ge.nlatsabidze.newsapplication.common.LoadImage
import ge.nlatsabidze.newsapplication.common.containsBraces
import ge.nlatsabidze.newsapplication.common.firstIndexOfOpenBrace
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding

interface Details {

    fun showDetails(binding: DetailsFragmentBinding, argsArticle: DetailsFragmentArgs)

    class Base @Inject constructor(
        private val imageLoader: LoadImage,
        @Named("firstItem") val dateFormatter: Mapper<String, String>
    ) : Details {
        override fun showDetails(binding: DetailsFragmentBinding, argsArticle: DetailsFragmentArgs) =
            with(binding) {
                with(argsArticle) {
                    imageLoader.load(binding.contentImage, articleargs.urlToImage)
                    date.text = articleargs.publishedAt
                    personName.text = articleargs.author
                    journalName.text = articleargs.source!!.name
                    date.text = articleargs.publishedAt?.let { dateFormatter.map(it) }
                    if (articleargs.content!!.containsBraces()) binding.newsContent.text =
                        articleargs.content!!.substring(
                            0,
                            articleargs.content!!.firstIndexOfOpenBrace()
                        )
                    else newsContent.text = articleargs.content
                    newsTitle.text = articleargs.title
                }
            }
    }
}
