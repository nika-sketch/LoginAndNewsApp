package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import androidx.viewbinding.ViewBinding
import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter

abstract class BaseItemNewsViewHolder(
    binding: ViewBinding,
    private val itemClickListener: OnItemClick<ArticleUi>,
) : BaseRecyclerViewAdapter.BaseViewHolder<ArticleUi>(binding.root) {

    override fun bind(item: ArticleUi) {
        onClick { itemClickListener.onItemClick(item) }
        onLongClick { itemClickListener.onLongItemClick(item) }
    }
}