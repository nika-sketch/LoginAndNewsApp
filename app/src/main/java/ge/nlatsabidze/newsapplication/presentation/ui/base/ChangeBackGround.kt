package ge.nlatsabidze.newsapplication.presentation.ui.base

import androidx.appcompat.app.AppCompatDelegate

// todo has to be implemented
interface ChangeBackGround {

    fun changeBackGround()

    abstract class Abstract(private val nightMode: Int) : ChangeBackGround {
        override fun changeBackGround() {
            AppCompatDelegate.setDefaultNightMode(nightMode)
        }
    }

    object Empty : ChangeBackGround {
        override fun changeBackGround() = Unit
    }

    class Dark : Abstract(AppCompatDelegate.MODE_NIGHT_NO)
    class White : Abstract(AppCompatDelegate.MODE_NIGHT_YES)
}
