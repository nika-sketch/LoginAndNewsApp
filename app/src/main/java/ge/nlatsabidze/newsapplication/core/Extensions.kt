package ge.nlatsabidze.newsapplication.core

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.onTap(block: () -> Unit) {
    this.setOnClickListener {
        block.invoke()
    }
}

fun View.showSnack(message: String, length: Int = Snackbar.LENGTH_SHORT) {
    val snack = Snackbar.make(this, message, length)
    snack.show()
}


fun String.firstIndexOfOpenBrace(): Int = this.indexOf('[')

fun String.containsBraces(): Boolean = this.contains('[')

