package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick

class NewsItemAdapter(
    private val itemCLickListener: OnItemClick<Article>
) : BaseRecyclerViewAdapter<Article>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == 1) FirstNewsItemViewHolder(
            FirstNewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemCLickListener
        )
        else NewsItemViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemCLickListener
        )

    override fun getItemViewType(position: Int): Int = if (position == 0) 1 else 2
}



