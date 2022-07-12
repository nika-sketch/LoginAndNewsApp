package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.core.onTap
import ge.nlatsabidze.newsapplication.core.Mapper
import ge.nlatsabidze.newsapplication.core.LoadImage
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.core.AbstractDateFormat
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter

class FirstNewsItemViewHolder(
    private val binding: FirstNewsItemBinding,
    private val itemClickListener: OnItemClickListener<Article>,
    private val imageLoader: LoadImage = LoadImage.GithubImageBase(),
    private val dateFormatter: Mapper<String, String> = AbstractDateFormat.DateFormatter()
) : RecyclerView.ViewHolder(binding.root), BaseRecyclerViewAdapter.Bind<Article> {

    override fun bind(item: Article) = with(binding) {
        item.urlToImage.let { imageLoader.load(contentImage, it) }
        publishedDate.text = item.publishedAt?.let { dateFormatter.map(it) }
        tvTitle.text = item.description
        newsDescription.text = item.title

        root.onTap {
            itemClickListener.onItemClick(item)
        }
    }
}
