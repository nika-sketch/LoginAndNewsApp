package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.common.dateFormatter
import ge.nlatsabidze.newsapplication.common.setImage
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding

class NewsItemAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var newsList: MutableList<Article> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onArticleClicked: ((Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) {
            FirstNewsItemViewHolder(FirstNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), newsList, onArticleClicked)
        } else {
            NewsItemViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), newsList, onArticleClicked)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            (holder as FirstNewsItemViewHolder).onBind()
        } else {
            (holder as NewsItemViewHolder).onBind()
        }
    }

    override fun getItemCount() = newsList.size

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 1
        }
        return 2
    }
}