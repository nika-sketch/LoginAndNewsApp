package ge.nlatsabidze.newsapplication.data.firebaseAuthService

import com.google.firebase.auth.FirebaseAuth
import ge.nlatsabidze.newsapplication.domain.firebaseAuthService.RegisterRepository
import ge.nlatsabidze.newsapplication.presentation.ui.firebaseAuthentication.UserAuthResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : RegisterRepository {

    override suspend fun register(name: String, email: String, password: String): UserAuthResult =
        try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            UserAuthResult.SuccessRegisterAuth()
        } catch (e: Exception) {
            UserAuthResult.ExceptionAuth(e.message!!)
        }
}