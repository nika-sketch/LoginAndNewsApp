package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import ge.nlatsabidze.newsapplication.core.*
import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.core.OnItemClick
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter

class NewsItemViewHolder(
    private val binding: NewsItemBinding,
    private val itemClickListener: OnItemClick<ArticleUi>,
    private val loadImage: LoadImage = LoadImage.GithubImageBase(),
    private val formatDate: Mapper<String, String> = AbstractDateFormat.DateFormatter()
) : BaseRecyclerViewAdapter.BaseViewHolder<ArticleUi>(binding.root)  {

    override fun bind(item: ArticleUi) = with(binding) {
        item.bindNewsItem(binding, loadImage, formatDate)
        itemView.onTap { itemClickListener.onItemClick(item) }
        itemView.onLongTap { itemClickListener.onLongItemClick(item)  }
    }
}
