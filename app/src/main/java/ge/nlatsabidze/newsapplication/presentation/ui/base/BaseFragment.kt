package ge.nlatsabidze.newsapplication.presentation.ui.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.app.AlertDialog
import kotlinx.coroutines.launch
import android.view.LayoutInflater
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.flow.Flow
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.flowWithLifecycle
import ge.nlatsabidze.newsapplication.R
import kotlinx.coroutines.flow.collectLatest

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    protected fun <T> collectFlow(flow: Flow<T>, onCollect: suspend (T) -> Unit) =
        viewLifecycleOwner.lifecycleScope.launch {
            flow.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collectLatest(onCollect)
        }

    protected fun showAlert(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(resources.getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
