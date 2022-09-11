package ge.nlatsabidze.newsapplication.presentation.ui.notification

import android.os.Build
import javax.inject.Inject
import android.content.Context
import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat

interface HandleNotification {

    fun createNotification(applicationContext: Context)

    class Base @Inject constructor(
        private val randomWordsDescription: RandomWordsDescription,
        private val notificationBuilder: BuildNotification,
        private val notificationChannel: ChannelNotification = ChannelNotification.Base()
    ) : HandleNotification {

        companion object {
            private const val NOTIFICATION_ID = 1
        }

        override fun createNotification(applicationContext: Context) {
            val notificationBuilder = notificationBuilder.apply(applicationContext)
            val content = randomWordsDescription.content()
            val notification = content.wordDescription(notificationBuilder)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationManager = applicationContext.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager
                notificationManager.createNotificationChannel(notificationChannel.provideNotificationChannel())
            }

            with(NotificationManagerCompat.from(applicationContext)) {
                notify(NOTIFICATION_ID, notification.build())
            }
        }
    }
}
