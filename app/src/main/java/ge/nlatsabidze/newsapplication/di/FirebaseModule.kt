package ge.nlatsabidze.newsapplication.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.data.signIn.SignInRepositoryImpl
import ge.nlatsabidze.newsapplication.domain.signIn.SignInRepository
import ge.nlatsabidze.newsapplication.presentation.ui.signIn.EventSignIn

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideSingInRepository(firebaseAuth: FirebaseAuth): SignInRepository =
        SignInRepositoryImpl(firebaseAuth)

    @Provides
    fun provideSignInChannel(): Communication<EventSignIn> = Communication.BaseSignInEvent()
}
