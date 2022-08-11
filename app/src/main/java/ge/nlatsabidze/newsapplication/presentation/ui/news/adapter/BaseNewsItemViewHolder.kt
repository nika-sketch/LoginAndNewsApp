package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.core.Mapper
import ge.nlatsabidze.newsapplication.core.onTap
import ge.nlatsabidze.newsapplication.core.LoadImage
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.core.AbstractDateFormat
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter

abstract class BaseNewsItemViewHolder(
    binding: ViewBinding,
    private val itemClickListener: OnItemClick<Article>,
    private val imageLoader: LoadImage = LoadImage.GithubImageBase(),
    private val dateFormatter: Mapper<String, String> = AbstractDateFormat.DateFormatter()
) : RecyclerView.ViewHolder(binding.root), BaseRecyclerViewAdapter.Bind<Article> {

    override fun bind(item: Article) {
        item.urlToImage.let { imageLoader.load(contentImage(), it) }
        publishedDate().text = item.publishedAt?.let { dateFormatter.map(it) }
        newsDescription().text = item.title

        itemView.onTap {
            itemClickListener.onItemClick(item)
        }
    }

    abstract fun contentImage(): ImageView
    abstract fun publishedDate(): TextView
    abstract fun newsDescription(): TextView
}