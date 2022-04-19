package ge.nlatsabidze.newsapplication.presentation.ui.details

import androidx.navigation.fragment.navArgs
import ge.nlatsabidze.newsapplication.common.containsBraces
import ge.nlatsabidze.newsapplication.common.dateFormatter
import ge.nlatsabidze.newsapplication.common.removeBraces
import ge.nlatsabidze.newsapplication.common.setImage
import ge.nlatsabidze.newsapplication.data.model.Article
import ge.nlatsabidze.newsapplication.databinding.DetailsFragmentBinding
import ge.nlatsabidze.newsapplication.presentation.ui.base.BaseFragment

class DetailsFragment : BaseFragment<DetailsFragmentBinding>(DetailsFragmentBinding::inflate) {

    private val argsArticle: DetailsFragmentArgs by navArgs()
    private lateinit var article: Article

    override fun start() {
        article = argsArticle.articleargs

        binding.apply {
            with(article) {
                contentImage.setImage(urlToImage)
                date.text = publishedAt
                personName.text = author
                journalName.text = source?.name
                date.text = publishedAt!!.dateFormatter()
                if (content?.containsBraces() == true) {
                    newsContent.text = content.substring(0, content.removeBraces())
                } else {
                    newsContent.text = content
                }
                binding.newsTitle.text = title
            }
        }
    }
}