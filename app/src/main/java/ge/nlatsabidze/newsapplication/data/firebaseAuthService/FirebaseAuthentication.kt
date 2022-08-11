package ge.nlatsabidze.newsapplication.data.firebaseAuthService

import com.google.firebase.auth.AuthResult
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult
import javax.inject.Inject

interface FirebaseAuthentication {

    suspend fun authenticateUser(email: String, password: String): UserAuthResult

    abstract class Abstract : FirebaseAuthentication {

        abstract fun userAuthResult(): UserAuthResult
        abstract suspend fun authResult(email: String, password: String): AuthResult

        override suspend fun authenticateUser(email: String, password: String): UserAuthResult =
            try {
                authResult(email, password)
                userAuthResult()
            } catch (e: Exception) {
                UserAuthResult.ExceptionAuth(e.message!!)
            }
    }

    class Register @Inject constructor(private val auth: Auth) : Abstract() {
        override suspend fun authResult(email: String, password: String): AuthResult =
            auth.register(email, password)

        override fun userAuthResult() = UserAuthResult.SuccessRegisterAuth()
    }

    class Login @Inject constructor(private val auth: Auth) : Abstract() {
        override suspend fun authResult(email: String, password: String): AuthResult =
            auth.signIn(email, password)

        override fun userAuthResult() = UserAuthResult.SuccessLoginAuth()
    }
}