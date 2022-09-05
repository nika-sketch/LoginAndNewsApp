package ge.nlatsabidze.newsapplication.presentation.ui.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import javax.inject.Inject

interface HandleNotification {

    fun createNotification(applicationContext: Context)

    class Base @Inject constructor(
        private val randomWordsDescription: RandomWordsDescription,
        private val notificationBuilder: BuildNotification
    ) : HandleNotification {

        companion object {
            private const val CHANNEL_ID = "channel_id"
            private const val NOTIFICATION_ID = 1
        }

        override fun createNotification(applicationContext: Context) {
            val notificationBuilder = notificationBuilder.apply(applicationContext)
            val content = randomWordsDescription.content()
            val notification = content.wordDescription(notificationBuilder)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelImportance = NotificationManager.IMPORTANCE_HIGH

                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "Channel Name",
                    channelImportance
                ).apply {
                    description = "Channel Description"
                }

                val notificationManager = applicationContext.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager

                notificationManager.createNotificationChannel(channel)
            }

            with(NotificationManagerCompat.from(applicationContext)) {
                notify(NOTIFICATION_ID, notification.build())
            }
        }
    }
}
