package ge.nlatsabidze.newsapplication.presentation.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

@SuppressLint("DiffUtilEquals")
class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.toString() == newItem.toString()
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
