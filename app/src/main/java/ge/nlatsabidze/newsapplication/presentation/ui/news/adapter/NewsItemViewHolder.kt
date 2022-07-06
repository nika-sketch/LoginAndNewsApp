package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.common.AbstractDateFormat
import ge.nlatsabidze.newsapplication.common.LoadImage
import ge.nlatsabidze.newsapplication.common.Mapper
import ge.nlatsabidze.newsapplication.common.onTap
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter

class NewsItemViewHolder(
    private val binding: NewsItemBinding,
    private val onItemClickListener: OnItemClickListener<Article>,
    private val dateFormatter: Mapper<String, String> = AbstractDateFormat.DateFormatter(),
    private val imageLoader: LoadImage = LoadImage.CircleImageBase()
) : RecyclerView.ViewHolder(binding.root), BaseRecyclerViewAdapter.Binder<Article> {

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
