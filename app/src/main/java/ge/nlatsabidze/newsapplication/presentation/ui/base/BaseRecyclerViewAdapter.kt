package ge.nlatsabidze.newsapplication.presentation.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.nlatsabidze.newsapplication.common.Mapper


abstract class BaseRecyclerViewAdapter<T>(val onClick: ((T) -> Unit)) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Mapper<List<T>, Unit> {

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    private var data: MutableList<T> = mutableListOf()

    override fun map(source: List<T>) {
        data.clear()
        data.addAll(source)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(parent, viewType)

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as Binder<T>).bind(data[position])

    override fun getItemCount() = data.size

    interface Binder<T> {
        fun bind(item: T)
    }

}