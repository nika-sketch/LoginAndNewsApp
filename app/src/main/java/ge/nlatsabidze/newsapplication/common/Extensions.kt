package ge.nlatsabidze.newsapplication.common

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun String.firstIndexOfOpenBrace(): Int = this.indexOf('[')

fun String.containsBraces(): Boolean = this.contains('[')

