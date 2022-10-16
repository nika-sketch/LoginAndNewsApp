package ge.nlatsabidze.newsapplication.presentation.ui.details

import ge.nlatsabidze.newsapplication.data.model.ArticleUi

interface ArticleDetails {

    interface Save<T> {
        fun save(data: T)
    }

    interface Read<T> {
        fun read(): T
    }

    interface Mutable<T>: Read<T>, Save<T>

    class Base : Mutable<ArticleUi> {

        private lateinit var articleUi: ArticleUi

        override fun save(data: ArticleUi) {
            this.articleUi = data
        }

        override fun read(): ArticleUi = articleUi
    }
}
