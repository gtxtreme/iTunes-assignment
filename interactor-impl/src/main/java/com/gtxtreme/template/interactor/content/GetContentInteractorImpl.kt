package com.gtxtreme.template.interactor.content

import com.gtxtreme.template.domain.base.Result
import com.gtxtreme.template.domain.content.Content
import com.gtxtreme.template.domain.content.GetContentUseCase
import com.wednesday.template.domain.weather.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class GetContentInteractorImpl(val getContentUseCase: GetContentUseCase) : GetContentInteractor {

    private val searchContentResultStateFlow = MutableSharedFlow<List<Content>>()

    override val searchResults: Flow<List<Content>>
        get() = searchContentResultStateFlow

    override suspend fun search(param: String) {
        val finalResult = when (val result = getContentUseCase(param)) {
            is Result.Success -> {
                result.data
            }
            is Result.Error -> {
                Timber.tag(TAG).e(result.exception, "search error")
                listOf()
            }
        }

        searchContentResultStateFlow.emit(finalResult)
    }

    companion object {
        const val TAG = "GetContentInteractorImpl"
    }

}