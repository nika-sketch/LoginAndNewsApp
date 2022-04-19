package ge.nlatsabidze.newsapplication.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ge.nlatsabidze.newsapplication.common.Dispatchers
import javax.inject.Inject

open class BaseViewModel @Inject constructor(private val dispatchers: Dispatchers): ViewModel() {

    fun <T> coroutineIoLauncher(block: suspend () -> T) {
        dispatchers.launchBackground(viewModelScope) {
            block.invoke()
        }
    }
}