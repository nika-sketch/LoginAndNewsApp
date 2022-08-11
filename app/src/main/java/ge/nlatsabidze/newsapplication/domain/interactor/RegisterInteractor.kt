package ge.nlatsabidze.newsapplication.domain.interactor

import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult

interface RegisterInteractor {

    suspend fun register(name: String, email: String, password: String): UserAuthResult
}