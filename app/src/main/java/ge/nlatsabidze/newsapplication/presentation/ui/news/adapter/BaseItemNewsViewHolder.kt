package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import androidx.viewbinding.ViewBinding
import ge.nlatsabidze.newsapplication.core.onLongTap
import ge.nlatsabidze.newsapplication.core.onTap
import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick

abstract class BaseItemNewsViewHolder(
    binding: ViewBinding,
    private val itemClickListener: OnItemClick<ArticleUi>,
) : BaseRecyclerViewAdapter.BaseViewHolder<ArticleUi>(binding.root) {

    override fun bind(item: ArticleUi) {
        itemView.onTap { itemClickListener.onItemClick(item) }
        itemView.onLongTap { itemClickListener.onLongItemClick(item) }
    }
}