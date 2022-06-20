package ge.nlatsabidze.newsapplication.presentation.ui.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsFragmentBinding>(DetailsFragmentBinding::inflate) {

    private val argsArticle: DetailsFragmentArgs by navArgs()
    private lateinit var article: Article

    @Inject
    @Named("stringMapper")
    lateinit var dateFormatter: Mapper<String, String>

    @Inject
    @Named("firstItem")
    lateinit var imageLoader: LoadImage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        article = argsArticle.articleargs
        with(article) {
            imageLoader.load(contentImage, urlToImage!!)
            date.text = publishedAt
            personName.text = author
            journalName.text = source!!.name
            date.text = publishedAt?.let { dateFormatter.map(it) }
            if (content!!.containsBraces()) newsContent.text =
                content.substring(0, content.firstIndexOfOpenBrace())
            else newsContent.text = content
            binding.newsTitle.text = title
        }
    }
}