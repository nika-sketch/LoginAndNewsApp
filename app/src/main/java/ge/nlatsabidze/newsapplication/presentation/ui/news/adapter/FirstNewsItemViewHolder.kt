package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import ge.nlatsabidze.newsapplication.common.dateFormatter
import ge.nlatsabidze.newsapplication.common.koinLoad
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter

class FirstNewsItemViewHolder(
    private val binding: FirstNewsItemBinding,
    private val onArticleClicked: ((Article) -> Unit)?,
    private val imageLoader: LoadImage = LoadImage.Base()
) : RecyclerView.ViewHolder(binding.root), BaseRecyclerViewAdapter.Binder<Article> {
    override fun bind(item: Article) = with(binding) {
        item.urlToImage?.let { imageLoader.load(contentImage, it) }
        publishedDate.text = item.publishedAt?.dateFormatter()
        tvTitle.text = item.description
        newsDescription.text = item.title

        root.setOnClickListener {
            onArticleClicked?.invoke(item)
        }
    }
}