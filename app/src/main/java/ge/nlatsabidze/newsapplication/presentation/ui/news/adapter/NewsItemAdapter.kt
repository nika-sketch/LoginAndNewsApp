package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter

class NewsItemAdapter(
    private val itemCLickListener: OnItemClick<ArticleUi>
) : BaseRecyclerViewAdapter<ArticleUi>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ArticleUi> {
        return if (viewType == 1) FirstNewsItemViewHolder(
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
    }

    override fun getItemViewType(position: Int): Int = if (position == 0) 1 else 2
}
