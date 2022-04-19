package ge.nlatsabidze.newsapplication.presentation.ui.news

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding>(NewsFragmentBinding::inflate) {

    private val newsViewModel: NewsViewModel by viewModels()

    override fun start() {
        binding.root.setOnClickListener {
            val action = NewsFragmentDirections.actionNewsFragmentToDetailsFragment()
            findNavController().navigate(action)
        }

        newsViewModel.getNews()
        viewLifecycleOwner.lifecycleScope.launch {
            newsViewModel.news.collect {
                d("AAAAAAA", it.data?.status.toString())
                binding.tvText.text = it.data?.articles.toString()
            }
        }
    }
}