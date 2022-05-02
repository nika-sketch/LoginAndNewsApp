package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.common.dateFormatter
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
        contentImage.setImage(article.urlToImage)
        publishedDate.text = article.publishedAt.toString().dateFormatter()
        desc.text = article.description.toString()
        newsDescription.text = article.title.toString()

        root.setOnClickListener {
            onArticleClicked?.invoke(article)
        }
    }
}
