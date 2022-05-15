package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding

abstract class BaseViewHolder<T, VB : ViewBinding>(binding: VB) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
}

class NewsItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var newsList: MutableList<Article> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onArticleClicked: ((Article) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == 1) FirstNewsItemViewHolder(
            FirstNewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onArticleClicked
        )
        else NewsItemViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onArticleClicked
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when {
        getItemViewType(position) == 1 -> (holder as FirstNewsItemViewHolder).bind(newsList[position])
        else -> (holder as NewsItemViewHolder).bind(newsList[position])
    }

    override fun getItemCount() = newsList.size

    override fun getItemViewType(position: Int): Int = if (position == 0) 1 else 2
}


