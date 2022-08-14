package ge.nlatsabidze.newsapplication.domain.model

import android.os.Parcelable
import ge.nlatsabidze.newsapplication.data.model.Article
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDomain(
    val articles: MutableList<Article>,
    val status: String,
) : Parcelable
