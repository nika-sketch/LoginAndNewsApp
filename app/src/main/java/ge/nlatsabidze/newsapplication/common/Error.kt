package ge.nlatsabidze.newsapplication.common

import ge.nlatsabidze.newsapplication.R

interface Error {

    fun message(): String

    abstract class ErrorAbstract(
        private val resourceManager: ProvideResources,
        private val resource: Int = R.string.app_name
    ) : Error {
        override fun message(): String = resourceManager.string(resource)
    }

    class NoConnection(resourceManager: ProvideResources) :
        ErrorAbstract(resourceManager, resource = R.string.no_internet_connection)

}