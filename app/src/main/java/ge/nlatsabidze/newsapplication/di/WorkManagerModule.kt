package ge.nlatsabidze.newsapplication.di

import dagger.Module
import androidx.work.*
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import java.util.concurrent.TimeUnit
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.FirebaseEvent
import ge.nlatsabidze.newsapplication.presentation.ui.notification.*

@Module
@InstallIn(SingletonComponent::class)
object WorkManagerModule {

    @Provides
    fun provideConstraints(): Constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .setRequiresCharging(false)
        .build()

    @Provides
    fun provideWorkRequest(constraints: Constraints): PeriodicWorkRequest =
        PeriodicWorkRequestBuilder<WorkService>(16, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

    @Provides
    fun provideExistingPeriodicWorkPolicy(): ExistingPeriodicWorkPolicy =
        ExistingPeriodicWorkPolicy.KEEP

    @Provides
    fun provideWorkName(): String = "Work"

    @Provides
    fun provideNotificationEvent(
        workRequest: PeriodicWorkRequest,
        workPolicy: ExistingPeriodicWorkPolicy,
        workName: String
    ): FirebaseEvent = FirebaseEvent.Notification(workRequest, workPolicy, workName)

    @Provides
    @Singleton
    fun provideHandleNotification(
        randomWordsDescription: RandomWordsDescription,
        notificationBuilder: BuildNotification
    ): HandleNotification = HandleNotification.Base(randomWordsDescription, notificationBuilder)

    @Provides
    fun provideGenerateRandomNumber(): GenerateRandomNumber<Int> = GenerateRandomNumber.Base()

    @Provides
    fun provideShuffleList(): ShuffleList<GenerateContent> = ShuffleList.RandomShuffle()

    @Provides
    fun provideRandomDescription(
        GenerateRandomNumber: GenerateRandomNumber<Int>,
        shuffleList: ShuffleList<GenerateContent>
    ): RandomWordsDescription = RandomWordsDescription.Base(GenerateRandomNumber, shuffleList)

    @Provides
    fun provideNotificationCompatPriority(): Int = 2

    @Provides
    fun provideNotificationBuilderCompat(notificationCompatPriority: Int): BuildNotification = BuildNotification.Base(notificationCompatPriority)

}
