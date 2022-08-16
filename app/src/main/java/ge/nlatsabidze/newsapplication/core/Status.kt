package ge.nlatsabidze.newsapplication.core

import android.view.View
import ge.nlatsabidze.newsapplication.R

abstract class Status {

    abstract fun apply(view: View)

    abstract class AbstractStatus(
        private val provideResources: ProvideResources,
        private val id: Int
    ) : Status() {
        override fun apply(view: View) {
            view.showSnack(provideResources.string(id))
        }
    }

    class Lost(provideResources: ProvideResources) : AbstractStatus(provideResources, R.string.no_internet_connection)
    class Available(provideResources: ProvideResources) : AbstractStatus(provideResources, R.string.available)
}