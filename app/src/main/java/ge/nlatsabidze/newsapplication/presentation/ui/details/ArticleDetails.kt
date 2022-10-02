package ge.nlatsabidze.newsapplication.presentation.ui.details

import ge.nlatsabidze.newsapplication.data.model.ArticleUi

interface ArticleDetails {

    interface Save {
        fun save(data: ArticleUi)
    }

    interface Read {
        fun read(): ArticleUi
    }

    interface Mutable: Read, Save

    class Base : Mutable {

        private lateinit var articleUi: ArticleUi

        override fun save(data: ArticleUi) {
            this.articleUi = data
        }

        override fun read(): ArticleUi = articleUi
    }
}
