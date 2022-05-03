package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ge.nlatsabidze.newsapplication.common.dateFormatter
import ge.nlatsabidze.newsapplication.common.koinLoad
import ge.nlatsabidze.newsapplication.common.setImage
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding

class NewsItemViewHolder(
    private val binding: NewsItemBinding,
    private val newsList: MutableList<Article>,
    private val onArticleClicked: ((Article) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var article: Article

    fun onBind() = with(binding) {
        article = newsList[bindingAdapterPosition]
        article.urlToImage?.let { contentImage.koinLoad(it) }
        publishedDate.text = article.publishedAt.toString().dateFormatter()
        desc.text = article.description.toString()
        newsDescription.text = article.title.toString()

        root.setOnClickListener {
            onArticleClicked?.invoke(article)
        }
    }
}
