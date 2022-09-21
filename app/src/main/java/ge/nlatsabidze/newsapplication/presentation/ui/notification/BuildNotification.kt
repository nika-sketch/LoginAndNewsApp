package ge.nlatsabidze.newsapplication.presentation.ui.notification

import javax.inject.Inject
import javax.inject.Named
import android.content.Context
import androidx.core.app.NotificationCompat

interface BuildNotification {

    fun apply(context: Context): NotificationCompat.Builder

    class Base @Inject constructor(
        private val notificationCompatPriority: Int,
        private val ringtoneManager: ProvideRingtoneManager,
        @Named("notificationVibration") private val notificationVibration: ProvideNotificationImageAndVibration,
        @Named("notificationImage") private val notificationImage: ProvideNotificationImageAndVibration
    ) : BuildNotification {

        companion object {
            private const val CHANNEL_ID = "channel_id"
        }

        override fun apply(context: Context): NotificationCompat.Builder =
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(notificationImage.provideVibrationAndIcon())
                .setPriority(notificationCompatPriority)
                .setAutoCancel(true)
                .setSound(ringtoneManager.ringToneNotification())
                .setDefaults(notificationVibration.provideVibrationAndIcon())
    }
}