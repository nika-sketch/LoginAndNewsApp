package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick

class NewsItemViewHolder(
    private val binding: NewsItemBinding,
    itemClickListener: OnItemClick<ArticleUi>,
) : BaseItemNewsViewHolder(binding, itemClickListener) {

    override fun bind(item: ArticleUi) = with(binding) {
        item.bindNewsItem(binding)
        super.bind(item)
    }
}
