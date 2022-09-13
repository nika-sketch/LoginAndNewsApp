package ge.nlatsabidze.newsapplication.data.model

import android.os.Parcelable
import ge.nlatsabidze.newsapplication.core.LoadImage
import ge.nlatsabidze.newsapplication.core.Mapper
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleUi(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
) : Parcelable {

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
}
