package ge.nlatsabidze.newsapplication.common

import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import ge.nlatsabidze.newsapplication.R
import javax.inject.Inject

interface LoadImage {

    fun load(imageView: ImageView, url: String)

    abstract class Abstract(
        private val placeHolder: Int = R.drawable.ic_round_menu,
        private val allowCrossFade: Boolean = true,
        private val crossFadeDuration: Int = 500,
        private val transformations: Transformation = RoundedCornersTransformation(10f)
    ) :
        LoadImage {

        override fun load(imageView: ImageView, url: String) {
            imageView.load(url) {
                placeholder(placeHolder)
                crossfade(allowCrossFade)
                crossfade(crossFadeDuration)
                transformations(transformations)
            }
        }
    }

    class FirstItemBase : Abstract(placeHolder = R.drawable.ic_github, crossFadeDuration = 1000)
    class SecondItemBase : Abstract(crossFadeDuration = 200, transformations = CircleCropTransformation())
}