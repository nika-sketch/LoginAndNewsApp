package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import android.widget.ImageView
import android.widget.TextView
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
    onItemClickListener: OnItemClick<Article>
) : BaseNewsItemViewHolder(binding, onItemClickListener) {

    override fun bind(item: Article) = with(binding) {
        super.bind(item)
        desc.text = item.description
    }

    override fun contentImage(): ImageView = binding.contentImage
    override fun publishedDate(): TextView = binding.publishedDate
    override fun newsDescription(): TextView = binding.newsDescription
}
