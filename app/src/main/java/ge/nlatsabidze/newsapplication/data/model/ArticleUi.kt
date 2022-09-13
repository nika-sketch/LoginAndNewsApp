package ge.nlatsabidze.newsapplication.data.model

import android.net.Uri
import android.os.Parcelable
import ge.nlatsabidze.newsapplication.core.LoadImage
import ge.nlatsabidze.newsapplication.core.Mapper
import ge.nlatsabidze.newsapplication.core.containsBraces
import ge.nlatsabidze.newsapplication.core.firstIndexOfOpenBrace
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.details.DetailsFragmentArgs
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleUi(
    private val author: String,
    private val content: String,
    private val description: String,
    private val publishedAt: String,
    private val source: Source,
    private val title: String,
    private val url: String,
    private val urlToImage: String
) : Parcelable {

    fun parseUri(): Uri = Uri.parse(url)
    
    fun bindFirstItem(
        binding: FirstNewsItemBinding,
        loadImage: LoadImage,
        mapper: Mapper<String, String>
    ) = with(binding) {
        publishedDate.text = mapper.map(publishedAt)
        newsDescription.text = description
        loadImage.load(contentImage, urlToImage)
        tvTitle.text = title
    }

    fun bindNewsItem(
        binding: NewsItemBinding,
        loadImage: LoadImage,
        mapper: Mapper<String, String>
    ) = with(binding) {
        publishedDate.text = mapper.map(publishedAt)
        newsDescription.text = description
        loadImage.load(contentImage, urlToImage)
        desc.text = description
    }

    fun bindDetails(
        binding: DetailsFragmentBinding,
        argsArticle: DetailsFragmentArgs,
        imageLoader: LoadImage,
        dateFormatter: Mapper<String, String>
    ) = with(binding) {
        with(argsArticle) {
            imageLoader.load(binding.contentImage, articleargs.urlToImage)
            date.text = articleargs.publishedAt
            personName.text = articleargs.author
            journalName.text = articleargs.source.name
            date.text = articleargs.publishedAt.let { dateFormatter.map(it) }
            if (articleargs.content.containsBraces()) binding.newsContent.text =
                articleargs.content.substring(
                    0,
                    articleargs.content.firstIndexOfOpenBrace()
                )
            else newsContent.text = articleargs.content
            newsTitle.text = articleargs.title
        }
    }
}
