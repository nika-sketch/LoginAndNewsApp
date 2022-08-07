package ge.nlatsabidze.newsapplication.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.nlatsabidze.newsapplication.core.Communication
import ge.nlatsabidze.newsapplication.core.ProvideResources
import ge.nlatsabidze.newsapplication.core.Visibility
import ge.nlatsabidze.newsapplication.data.signIn.SignInInteractorImpl
import ge.nlatsabidze.newsapplication.data.signIn.SignInRepositoryImpl
import ge.nlatsabidze.newsapplication.domain.interactor.SignInInteractor
import ge.nlatsabidze.newsapplication.domain.signIn.SignInRepository
import ge.nlatsabidze.newsapplication.presentation.ui.core.Text
import ge.nlatsabidze.newsapplication.presentation.ui.signIn.SignInEvent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideSingInRepository(
        firebaseAuth: FirebaseAuth,
        provideResources: ProvideResources
    ): SignInRepository =
        SignInRepositoryImpl(firebaseAuth)

    @Provides
    fun provideSignInChannel(): Communication<SignInEvent> = Communication.BaseSignInEvent()

    @Provides
    fun provideLoadingCommunication(): Communication<Visibility> =
        Communication.BaseLoading(Visibility.Gone())

    @Provides
    fun provideText(): Text = Text.Base()

    @Provides
    fun provideSignInInteractor(
        signInRepository: SignInRepository,
        provideResources: ProvideResources
    ): SignInInteractor = SignInInteractorImpl(signInRepository, provideResources)
}
