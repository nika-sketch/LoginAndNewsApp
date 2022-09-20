package ge.nlatsabidze.newsapplication.data.model

import android.net.Uri
import ge.nlatsabidze.newsapplication.core.*
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding

data class ArticleUi(
    private val author: String,
    private val content: String,
    private val description: String,
    private val publishedAt: String,
    private val source: Source,
    private val title: String,
    private val url: String,
    private val urlToImage: String,
    private val loadImage: LoadImage = LoadImage.GithubImageBase(),
    private val dateFormat: Mapper<String, String> = AbstractDateFormat.DateFormatter()
) {

    fun parseUri(): Uri = Uri.parse(url)

    fun bindFirstItem(
        binding: FirstNewsItemBinding,
    ) = with(binding) {
        publishedDate.text = dateFormat.map(publishedAt)
        newsDescription.text = description
        loadImage.load(contentImage, urlToImage)
        tvTitle.text = title
    }

    fun bindNewsItem(
        binding: NewsItemBinding,
    ) = with(binding) {
        publishedDate.text = dateFormat.map(publishedAt)
        newsDescription.text = description
        loadImage.load(contentImage, urlToImage)
        desc.text = description
    }

    fun bindDetails(
        binding: DetailsFragmentBinding,
    ) = with(binding) {
        loadImage.load(binding.contentImage, urlToImage)
        date.text = publishedAt
        personName.text = author
        journalName.text = source.name
        date.text = publishedAt.let { dateFormat.map(it) }
        newsContent.text = content
        newsTitle.text = title
    }
}
