package ge.nlatsabidze.newsapplication.common

import coil.load
import android.widget.ImageView
import androidx.navigation.NavController
import coil.transform.Transformation
import ge.nlatsabidze.newsapplication.R
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.flow.*
import java.io.IOException

interface LoadImage {

    fun load(imageView: ImageView, url: String?)

    abstract class Abstract(
        private val placeHolder: Int = R.drawable.ic_round_menu,
        private val allowCrossFade: Boolean = true,
        private val crossFadeDuration: Int = 500,
        private val transformations: Transformation = RoundedCornersTransformation(10f)
    ) : LoadImage {

        override fun load(imageView: ImageView, url: String?) {
            imageView.load(url) {
                placeholder(placeHolder)
                crossfade(allowCrossFade)
                crossfade(crossFadeDuration)
                transformations(transformations)
            }
        }
    }

    class GithubImageBase : Abstract(placeHolder = R.drawable.ic_github, crossFadeDuration = 700)
    class CircleImageBase : Abstract(crossFadeDuration = 500)
}