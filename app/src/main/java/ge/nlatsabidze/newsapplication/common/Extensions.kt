package ge.nlatsabidze.newsapplication.common

import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun String.firstIndexOfOpenBrace(): Int = this.indexOf('[')

fun String.containsBraces(): Boolean = this.contains('[')

@SuppressLint("SimpleDateFormat")
abstract class AbstractDateFormat(
    private val dateInput: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
    private val dateOutPut: String = "dd-MM-yyyy"
) : Mapper<String, String> {
    override fun map(source: String): String {
        val dateInput = SimpleDateFormat(dateInput)
        val dateOutput = SimpleDateFormat(dateOutPut)
        val date: Date = dateInput.parse(source)
        return dateOutput.format(date)
    }
}

class DateFormatter : AbstractDateFormat()
