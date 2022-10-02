package ge.nlatsabidze.newsapplication.presentation.ui.base

import android.view.View
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T : Any> :
    ListAdapter<T, BaseRecyclerViewAdapter.BaseViewHolder<T>>(BaseItemCallback<T>()) {
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) =
        holder.bind(getItem(position))

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)

        protected fun onLongClick(block: () -> Unit) = itemView.setOnLongClickListener {
            block.invoke()
            true
        }

        protected fun onClick(block: () -> Unit) = itemView.setOnClickListener {
            block.invoke()
        }
    }
}

