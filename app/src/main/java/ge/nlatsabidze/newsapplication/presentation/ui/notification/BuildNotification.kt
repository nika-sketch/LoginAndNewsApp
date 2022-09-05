package ge.nlatsabidze.newsapplication.presentation.ui.notification

import android.content.Context
import androidx.core.app.NotificationCompat
import ge.nlatsabidze.newsapplication.R
import javax.inject.Inject

interface BuildNotification {

    fun apply(context: Context): NotificationCompat.Builder

    class Base @Inject constructor(
        private val notificationCompatPriority: Int
    ) : BuildNotification {

        companion object {
            private const val CHANNEL_ID = "channel_id"
        }

        override fun apply(context: Context): NotificationCompat.Builder =
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_github)
                .setPriority(notificationCompatPriority)
                .setAutoCancel(true)
    }
}
