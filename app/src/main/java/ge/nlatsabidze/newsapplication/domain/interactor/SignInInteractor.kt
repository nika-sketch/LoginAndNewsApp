package ge.nlatsabidze.newsapplication.domain.interactor

import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult

interface SignInInteractor {

    suspend fun signIn(email: String, password: String): UserAuthResult
}
