package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.common.DateFormatter
import ge.nlatsabidze.newsapplication.common.LoadImage
import ge.nlatsabidze.newsapplication.common.Mapper
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseRecyclerViewAdapter

class NewsItemViewHolder(
    private val binding: NewsItemBinding,
    private val onArticleClicked: ((Article) -> Unit)?,
    private val dateFormatter: Mapper<String, String> = DateFormatter(),
    private val imageLoader: LoadImage = LoadImage.SecondItemBase()
) : RecyclerView.ViewHolder(binding.root), BaseRecyclerViewAdapter.Binder<Article>{

    override fun bind(item: Article) = with(binding) {
        item.urlToImage.let { imageLoader.load(contentImage, it!!) }
        publishedDate.text = item.publishedAt?.let { dateFormatter.map(it) }
        desc.text = item.description
        newsDescription.text = item.title

        root.setOnClickListener {
            onArticleClicked?.invoke(item)
        }
    }
}
