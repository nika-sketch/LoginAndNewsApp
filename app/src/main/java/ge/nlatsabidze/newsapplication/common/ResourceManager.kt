package ge.nlatsabidze.newsapplication.common

import android.content.Context
import androidx.annotation.StringRes

interface ResourceManager {
    fun provide(@StringRes stringResId: Int): String

    class Base(
        private val context: Context
    ) : ResourceManager {
        override fun provide(@StringRes stringResId: Int): String = context.getString(stringResId)
    }
}