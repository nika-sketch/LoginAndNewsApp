package ge.nlatsabidze.newsapplication.presentation.ui.core

import android.widget.TextView

interface Text {

    fun text(view: TextView): String

    class Base : Text {
        override fun text(view: TextView) = view.text.toString().trim()
    }
}