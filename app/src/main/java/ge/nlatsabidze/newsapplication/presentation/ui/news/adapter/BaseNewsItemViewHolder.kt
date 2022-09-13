package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import android.widget.TextView
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import ge.nlatsabidze.newsapplication.core.*
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter

//abstract class BaseNewsItemViewHolder(
//    binding: ViewBinding,
//    private val itemClickListener: OnItemClick<ArticleUi>,
//    private val imageLoader: LoadImage = LoadImage.GithubImageBase(),
//    private val dateFormatter: Mapper<String, String> = AbstractDateFormat.DateFormatter()
//) : RecyclerView.ViewHolder(binding.root), BaseRecyclerViewAdapter.Bind<ArticleUi> {
//
//    override fun bind(item: ArticleUi) {
//        imageLoader.load(contentImage(), item.urlToImage)
//        publishedDate().text = dateFormatter.map(item.publishedAt)
//        newsDescription().text = item.title
//        itemView.onTap { itemClickListener.onItemClick(item) }
//        itemView.onLongTap { itemClickListener.onLongItemClick(item)  }
//    }
//
//    abstract fun contentImage(): ImageView
//    abstract fun publishedDate(): TextView
//    abstract fun newsDescription(): TextView
//}