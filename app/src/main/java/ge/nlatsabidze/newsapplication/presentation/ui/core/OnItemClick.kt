package ge.nlatsabidze.newsapplication.presentation.ui.core

interface OnItemClick<T> {

    fun onItemClick(item: T)

    fun onLongItemClick(url: T)
}
