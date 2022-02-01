package com.gtxtreme.template.interactor.content

import com.gtxtreme.template.domain.base.Result
import com.gtxtreme.template.domain.content.Content
import com.gtxtreme.template.domain.content.GetContentUseCase
import com.gtxtreme.template.interactor.base.CoroutineContextController
import com.gtxtreme.template.presentation.base.UIList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class GetContentInteractorImpl(
    val getContentUseCase: GetContentUseCase,
    private val coroutineContextController: CoroutineContextController,
    private val uiContentListResultsMapper: UIContentListResultsMapper,
) : GetContentInteractor {

    private val searchContentResultStateFlow = MutableSharedFlow<List<Content>>()

    override val searchResults: Flow<UIList>
        get() = searchContentResultStateFlow.map {
            uiContentListResultsMapper.map(it)
        }

    override suspend fun search(param: String) {
        coroutineContextController.switchToDefault {
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
    }

    companion object {
        const val TAG = "GetContentInteractorImpl"
    }
}
