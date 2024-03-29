package ge.nlatsabidze.newsapplication.core

import coil.load
import android.widget.ImageView
import androidx.core.content.ContextCompat
import coil.transform.Transformation
import ge.nlatsabidze.newsapplication.R
import coil.transform.RoundedCornersTransformation

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
                listener(onError = { _, _ ->
                    imageView.handleImageError()
                })
                placeholder(placeHolder)
                crossfade(allowCrossFade)
                crossfade(crossFadeDuration)
                transformations(transformations)
            }
        }
    }

    class GithubImageBase : Abstract(placeHolder = R.drawable.ic_github, crossFadeDuration = 700)
    class CircleImageBase : Abstract(placeHolder = R.drawable.ic_github, crossFadeDuration = 500)
}