package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.common.DateFormatter
import ge.nlatsabidze.newsapplication.common.Mapper
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter

class FirstNewsItemViewHolder(
    private val binding: FirstNewsItemBinding,
    private val onArticleClicked: ((Article) -> Unit)?,
    private val imageLoader: LoadImage = LoadImage.Base(),
    private val dateFormatter: Mapper<String, String> = DateFormatter()
) : RecyclerView.ViewHolder(binding.root), BaseRecyclerViewAdapter.Binder<Article> {
    override fun bind(item: Article) = with(binding) {
        item.urlToImage.let { imageLoader.load(contentImage, it) }
        publishedDate.text = dateFormatter.map(item.publishedAt)
        tvTitle.text = item.description
        newsDescription.text = item.title

        root.setOnClickListener {
            onArticleClicked?.invoke(item)
        }
    }
}