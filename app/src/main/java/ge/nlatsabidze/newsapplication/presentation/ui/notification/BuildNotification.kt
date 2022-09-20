package ge.nlatsabidze.newsapplication.presentation.ui.notification

import javax.inject.Inject
import android.content.Context
import android.app.Notification
import ge.nlatsabidze.newsapplication.R
import androidx.core.app.NotificationCompat

interface BuildNotification {

    fun apply(context: Context): NotificationCompat.Builder

    class Base @Inject constructor(
        private val notificationCompatPriority: Int,
        private val ringtoneManager: ProvideRingtoneManager,
    ) : BuildNotification {

        companion object {
            private const val CHANNEL_ID = "channel_id"
        }

        override fun apply(context: Context): NotificationCompat.Builder =
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_github)
                .setPriority(notificationCompatPriority)
                .setAutoCancel(true)
                .setSound(ringtoneManager.ringToneNotification())
                .setDefaults(Notification.DEFAULT_VIBRATE)
    }
}
