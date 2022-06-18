package ge.nlatsabidze.newsapplication.presentation.ui.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import ge.nlatsabidze.newsapplication.common.*
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment

class DetailsFragment : BaseFragment<DetailsFragmentBinding>(DetailsFragmentBinding::inflate) {

    private val argsArticle: DetailsFragmentArgs by navArgs()

    private lateinit var article: Article
    private lateinit var dateFormatter: Mapper<String, String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        article = argsArticle.articleargs
        dateFormatter = DateFormatter()

        with(binding) {
            with(article) {
                contentImage.koinLoad(urlToImage)
                date.text = publishedAt
                personName.text = author
                journalName.text = source.name
                date.text = dateFormatter.map(publishedAt)
                if (content.containsBraces()) newsContent.text =
                    content.substring(0, content.firstIndexOfOpenBrace())
                else newsContent.text = content
                binding.newsTitle.text = title
            }
        }
    }
}