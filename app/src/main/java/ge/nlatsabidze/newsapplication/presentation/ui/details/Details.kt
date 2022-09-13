package ge.nlatsabidze.newsapplication.presentation.ui.details

import javax.inject.Named
import javax.inject.Inject
import ge.nlatsabidze.newsapplication.core.Mapper
import ge.nlatsabidze.newsapplication.core.LoadImage
import ge.nlatsabidze.newsapplication.core.containsBraces
import ge.nlatsabidze.newsapplication.core.firstIndexOfOpenBrace
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding

interface Details {

    fun showDetails(binding: DetailsFragmentBinding, argsArticle: DetailsFragmentArgs)

    class Base @Inject constructor(
        private val imageLoader: LoadImage,
        @Named("firstItem") val dateFormatter: Mapper<String, String>
    ) : Details {
        override fun showDetails(
            binding: DetailsFragmentBinding,
            argsArticle: DetailsFragmentArgs
        ) = argsArticle.articleargs.bindDetails(binding, argsArticle, imageLoader, dateFormatter)
    }
}
