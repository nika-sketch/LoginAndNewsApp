package ge.nlatsabidze.newsapplication.common

import android.content.Context
import androidx.annotation.StringRes

interface ResourceManager {
    fun provide(@StringRes stringResId: Int): String

    abstract class Abstract(private val context: Context) : ResourceManager {
        override fun provide(@StringRes stringResId: Int): String = context.getString(stringResId)
    }

    class Base(context: Context) : Abstract(context)
}