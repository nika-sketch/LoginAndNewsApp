package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.core.onTap
import ge.nlatsabidze.newsapplication.core.Mapper
import ge.nlatsabidze.newsapplication.core.LoadImage
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.core.AbstractDateFormat
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick

class NewsItemViewHolder(
    private val binding: NewsItemBinding,
    private val onItemClickListener: OnItemClick<Article>,
    private val dateFormatter: Mapper<String, String> = AbstractDateFormat.DateFormatter(),
    private val imageLoader: LoadImage = LoadImage.CircleImageBase()
) : RecyclerView.ViewHolder(binding.root), BaseRecyclerViewAdapter.Bind<Article> {

    override fun bind(item: Article) = with(binding) {
        item.urlToImage.let { imageLoader.load(contentImage, it) }
        publishedDate.text = item.publishedAt?.let { dateFormatter.map(it) }
        desc.text = item.description
        newsDescription.text = item.title

        root.onTap {
            onItemClickListener.onItemClick(item)
        }
    }
}
