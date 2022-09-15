package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick

class FirstNewsItemViewHolder(
    private val binding: FirstNewsItemBinding,
    itemClickListener: OnItemClick<ArticleUi>,
) : BaseItemNewsViewHolder(binding, itemClickListener) {

    override fun bind(item: ArticleUi) = with(binding) {
        item.bindFirstItem(binding)
        super.bind(item)
    }
}
