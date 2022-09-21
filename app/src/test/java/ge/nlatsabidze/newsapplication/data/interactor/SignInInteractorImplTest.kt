package ge.nlatsabidze.newsapplication.data.interactor

import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import ge.nlatsabidze.newsapplication.core.ProvideResources
import ge.nlatsabidze.newsapplication.domain.interactor.SignInInteractor
import ge.nlatsabidze.newsapplication.domain.firebaseAuthService.SignInRepository
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult

class SignInInteractorImplTest : TestCase() {

    fun testSignInInteractor() = runBlocking {

        val fakeSignInRepository = FakeSignInRepository()
        val fakeProvideResources = FakeProvideResources()

        val signInInteractor: SignInInteractor =
            SignInInteractorImpl(fakeSignInRepository, fakeProvideResources)

        val actual = signInInteractor.signIn("nika@gmail.com", "123123")
        assertEquals(UserAuthResult.SuccessLoginAuth(), actual)

    }
}

class FakeSignInRepository : SignInRepository {
    override suspend fun signIn(email: String, password: String): UserAuthResult =
        UserAuthResult.SuccessLoginAuth()
}

class FakeProvideResources : ProvideResources {
    override fun string(id: Int): String = "Input is clear"
}