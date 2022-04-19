package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.common.setImage
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding

class NewsItemAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val firstItemView = 1
    }

    var newsList: MutableList<Article> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class FirstNewsItemViewHolder(private val binding: FirstNewsItemBinding): RecyclerView.ViewHolder(binding.root) {
        private lateinit var article: Article

        fun onBind() {
            article = newsList[bindingAdapterPosition]
            binding.contentImage.setImage(article.urlToImage)
            binding.publishedDate.text = article.publishedAt.toString()
            binding.tvTitle.text = article.description.toString()
            binding.newsDescription.text = article.title.toString()
        }
    }

    inner class NewsItemViewHolder(private val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root) {
        private lateinit var article: Article

        fun onBind() {
            article = newsList[bindingAdapterPosition]
            binding.contentImage.setImage(article.urlToImage)
            binding.publishedDate.text = article.publishedAt.toString()
            binding.desc.text = article.description.toString()
            binding.newsDescription.text = article.title.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == firstItemView) {
            FirstNewsItemViewHolder(FirstNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        } else {
            NewsItemViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == firstItemView) {
            (holder as FirstNewsItemViewHolder).onBind()
        } else {
            (holder as NewsItemViewHolder).onBind()
        }
    }

    override fun getItemCount() = newsList.size

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 1
        }
        return 2
    }
}