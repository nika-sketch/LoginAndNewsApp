package ge.nlatsabidze.newsapplication.presentation.ui.details

import ge.nlatsabidze.newsapplication.common.LoadImage
import ge.nlatsabidze.newsapplication.common.Mapper
import ge.nlatsabidze.newsapplication.common.containsBraces
import ge.nlatsabidze.newsapplication.common.firstIndexOfOpenBrace
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding
import javax.inject.Inject
import javax.inject.Named

interface Details {

    fun setDetails(binding: DetailsFragmentBinding, argsArticle: DetailsFragmentArgs)

    class Base @Inject constructor(
        private val imageLoader: LoadImage,
        @Named("firstItem") val dateFormatter: Mapper<String, String>
    ) : Details {
        override fun setDetails(binding: DetailsFragmentBinding, argsArticle: DetailsFragmentArgs) {
            imageLoader.load(binding.contentImage, argsArticle.articleargs.urlToImage)
            binding.date.text = argsArticle.articleargs.publishedAt
            binding.personName.text = argsArticle.articleargs.author
            binding.journalName.text = argsArticle.articleargs.source!!.name
            binding.date.text = argsArticle.articleargs.publishedAt?.let { dateFormatter.map(it) }
            if (argsArticle.articleargs.content!!.containsBraces()) binding.newsContent.text =
                argsArticle.articleargs.content!!.substring(
                    0,
                    argsArticle.articleargs.content!!.firstIndexOfOpenBrace()
                )
            else binding.newsContent.text = argsArticle.articleargs.content
            binding.newsTitle.text = argsArticle.articleargs.title
        }
    }
}
