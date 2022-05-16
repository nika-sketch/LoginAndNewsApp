package ge.nlatsabidze.newsapplication.presentation.ui.news.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsItemBinding
import ge.nlatsabidze.newsapplication.databinding.FirstNewsItemBinding


class NewsItemAdapter : BaseRecyclerViewAdapter<Article>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1) FirstNewsItemViewHolder(
            FirstNewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClicked
        )
        else NewsItemViewHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClicked
        )
    }

    override fun getItemViewType(position: Int): Int = if (position == 0) 1 else 2
}


abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClicked: ((T) -> Unit)? = null

    var data: List<T> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(parent, viewType)

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as Binder<T>).bind(data[position])

    override fun getItemCount(): Int = data.size

    interface Binder<T> {
        fun bind(item: T)
    }

}

