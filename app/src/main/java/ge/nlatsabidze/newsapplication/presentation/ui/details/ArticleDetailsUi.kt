package ge.nlatsabidze.newsapplication.presentation.ui.details

import ge.nlatsabidze.newsapplication.data.model.ArticleUi
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding

interface ArticleDetailsUi {

    fun apply(
        binding: DetailsFragmentBinding,
    )

    object Empty : ArticleDetailsUi {
        override fun apply(
            binding: DetailsFragmentBinding,
        ) = Unit
    }

    class Saved(private val articleUi: ArticleUi) : ArticleDetailsUi {
        override fun apply(
            binding: DetailsFragmentBinding,
        ) = articleUi.bindDetails(binding)
    }
}
