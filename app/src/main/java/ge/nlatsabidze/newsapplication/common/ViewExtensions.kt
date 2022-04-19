package ge.nlatsabidze.newsapplication.common

import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.children
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ge.nlatsabidze.newsapplication.R

fun ImageView.setImage(url:String?) {
    Glide.with(context).load(url).placeholder(R.drawable.ic_round_menu).into(this)
}

fun showDialogError(message: String, context: Context) {
    val builder = AlertDialog.Builder(context)
    builder.setMessage(message)
    builder.setPositiveButton("yes") { _: DialogInterface, _: Int -> }
    builder.show()
}

fun onSnack(view: View, text: String, color: Int) {
    val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
    snackbar.setActionTextColor(Color.BLUE)
    val snackbarView = snackbar.view
    snackbarView.setBackgroundColor(color)
    val textView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.textSize = 10f
    snackbar.show()
}



fun changeVisibility(views: List<View>, visibility: Int) {
    for (view in views) {
        view.visibility = visibility
    }
}

fun ViewGroup.hideAll() {
    this.children.forEach {
        if (it.tag == "error")
            it.visible()
        else
            it.gone()
    }
}
fun ViewGroup.showAll() {
    this.children.forEach {
        it.visible()
    }
}

fun View.visible(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.gone(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}