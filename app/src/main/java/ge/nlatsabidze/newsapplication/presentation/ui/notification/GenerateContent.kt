package ge.nlatsabidze.newsapplication.presentation.ui.notification

import androidx.core.app.NotificationCompat

interface GenerateContent {

    fun wordDescription(notificationBuilder: NotificationCompat.Builder): NotificationCompat.Builder

    data class Words(
        private val word: String,
        private val description: String
    ) : GenerateContent {

        override fun wordDescription(notificationBuilder: NotificationCompat.Builder): NotificationCompat.Builder =
            notificationBuilder
                .setContentTitle(word)
                .setContentText(description)
    }
}
