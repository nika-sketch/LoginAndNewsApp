package ge.nlatsabidze.newsapplication.domain.interactor

import ge.nlatsabidze.newsapplication.presentation.ui.signIn.UserAuthResult

interface SignInInteractor {

    suspend fun signIn(email: String, password: String): UserAuthResult
}
