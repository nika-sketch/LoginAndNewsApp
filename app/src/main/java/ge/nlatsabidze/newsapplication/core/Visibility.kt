package ge.nlatsabidze.newsapplication.core

import android.view.View

interface Visibility {

    fun apply(view: View)

    abstract class Abstract(private val visibility: Int) : Visibility {
        override fun apply(view: View) {
            view.visibility = visibility
        }
    }

    class Visible : Abstract(View.VISIBLE)
    class Invisible: Abstract(View.INVISIBLE)
    class Gone : Abstract(View.GONE)
}