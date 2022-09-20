package ge.nlatsabidze.newsapplication.core

import android.view.View
import android.widget.ImageView
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import ge.nlatsabidze.newsapplication.R
import androidx.lifecycle.viewModelScope
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.onTap(block: () -> Unit) {
    this.setOnClickListener {
        block.invoke()
    }
}

fun View.isEnabledAndClickable(boolean: Boolean) {
    this.isClickable = boolean
    this.isEnabled = boolean
}

fun ImageView.handleImageError() {
    this.setImageDrawable(
        ContextCompat.getDrawable(
            this.context,
            R.drawable.ic_github
        )
    )
}

fun View.onLongTap(block: () -> Unit) {
    this.setOnLongClickListener {
        block.invoke()
        true
    }
}

fun View.showSnack(message: String, length: Int = Snackbar.LENGTH_SHORT) {
    val snack = Snackbar.make(this, message, length)
    snack.show()
}

fun String.firstIndexOfOpenBrace(): Int = this.indexOf('[')

fun String.containsBraces(): Boolean = this.contains('[')

fun ViewModel.launchMain(block: suspend () -> Unit) = viewModelScope.launch { block.invoke() }
