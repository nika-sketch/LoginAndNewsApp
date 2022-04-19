package ge.nlatsabidze.newsapplication.common

import android.annotation.SuppressLint
import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.children
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ge.nlatsabidze.newsapplication.R
import java.text.SimpleDateFormat
import java.util.*

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
    snackbar.view.setBackgroundColor(color)
    val view: View = snackbar.getView()
    val textView =
        snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    val params = view.layoutParams as FrameLayout.LayoutParams
    view.layoutParams = params
    params.gravity = Gravity.TOP
    textView.textSize = 13f
    snackbar.show()
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

@SuppressLint("SimpleDateFormat")
fun String.dateFormatter(): String {
    val dateInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val dateOutput = SimpleDateFormat("dd-MM-yyyy")
    val date: Date = dateInput.parse(this)
    return dateOutput.format(date)
}

fun String.removeBraces(): Int {
    var item = this
    var index = 0
    for (i in item.indices) {
        if (item[i] == '[') {
            index = i
        }
    }
    return index
}

fun String.containsBraces(): Boolean {
    var item = this
    for (i in item.indices) {
        if (item[i] == '[') {
            return true
        }
    }
    return false
}