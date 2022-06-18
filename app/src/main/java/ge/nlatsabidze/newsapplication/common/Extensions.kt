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
import coil.load
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ge.nlatsabidze.newsapplication.R
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.setImage(url: String?) {
    Glide.with(context).load(url).placeholder(R.drawable.ic_round_menu).into(this)
}

fun ImageView.koinLoad(url: String) {
    this.load(url) {
        placeholder(R.drawable.ic_round_menu)
        crossfade(true)
        crossfade(500)
        transformations(RoundedCornersTransformation(10f))
    }
}

fun View.visible(): View {
    visibility = View.VISIBLE
    return this
}

fun View.gone(): View {
    visibility = View.GONE
    return this
}

class DateFormatter: Mapper<String, String> {
    override fun map(source: String): String {
        val dateInput = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateOutput = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = dateInput.parse(source)
        return dateOutput.format(date)
    }
}

fun String.firstIndexOfOpenBrace(): Int {
    return this.indexOf('[')
}

fun String.containsBraces(): Boolean {
    return this.contains('[')
}