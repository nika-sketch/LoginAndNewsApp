package ge.nlatsabidze.newsapplication.domain.interactor

import ge.nlatsabidze.newsapplication.core.Result
import ge.nlatsabidze.newsapplication.domain.model.NewsDomain
import kotlinx.coroutines.flow.Flow

interface NewsInteractor {

    fun execute(): Flow<Result<NewsDomain>>
}
