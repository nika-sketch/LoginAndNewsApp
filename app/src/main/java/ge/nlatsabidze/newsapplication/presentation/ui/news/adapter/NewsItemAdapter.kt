package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter

class NewsItemAdapter(onClick: (Article) -> Unit) : BaseRecyclerViewAdapter<Article>(onClick) {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) FirstNewsItemViewHolder(
            FirstNewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClick
        )
        else NewsItemViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClick
        )
    }

    override fun getItemViewType(position: Int): Int = if (position == 0) 1 else 2
}



