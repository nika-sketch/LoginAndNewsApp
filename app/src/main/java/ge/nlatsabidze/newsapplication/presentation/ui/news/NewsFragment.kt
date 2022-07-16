package ge.nlatsabidze.newsapplication.presentation.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.NewsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.NewsItemAdapter
import ge.nlatsabidze.newsapplication.presentation.ui.news.adapter.OnItemClickListener

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentBinding>(NewsFragmentBinding::inflate),
    OnItemClickListener<Article> {

    private val viewModel: NewsViewModel by activityViewModels()
    private lateinit var newsAdapter: NewsItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = with(binding) {
        newsAdapter = NewsItemAdapter(this@NewsFragment)
        binding.rvNews.adapter = newsAdapter

        viewModel.collectNews(viewLifecycleOwner) { it.apply(binding, newsAdapter) }
        viewModel.collectNavigation(viewLifecycleOwner) { it.navigate(findNavController()) }
    }

    override fun onItemClick(item: Article) {
        viewModel.saveAndNavigateToDetails(item)
    }
}
