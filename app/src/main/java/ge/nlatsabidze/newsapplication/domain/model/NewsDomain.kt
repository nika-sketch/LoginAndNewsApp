package ge.nlatsabidze.newsapplication.domain.model

import android.os.Parcelable
import androidx.room.Embedded
import ge.nlatsabidze.newsapplication.data.model.Article
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDomain(
    @Embedded val articles: MutableList<Article>,
) : Parcelable
