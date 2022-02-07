package com.gtxtreme.template.interactor.content

import com.gtxtreme.template.domain.base.Result
import com.gtxtreme.template.domain.content.Content
import com.gtxtreme.template.domain.content.GetContentUseCase
import com.gtxtreme.template.domain.content.GetFavouriteContentUseCase
import com.gtxtreme.template.interactor.base.CoroutineContextController
import com.gtxtreme.template.presentation.base.UIList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class GetContentInteractorImpl(
    val getContentUseCase: GetContentUseCase,
    private val coroutineContextController: CoroutineContextController,
    getFavouriteContentUseCase: GetFavouriteContentUseCase,
    private val uiContentListResultsMapper: UIContentListResultsMapper,
) : GetContentInteractor {

    private val searchContentResultStateFlow = MutableSharedFlow<List<Content>>()

    override val searchResults: Flow<UIList> = getFavouriteContentUseCase(Unit)
        .combine(searchContentResultStateFlow) { favoriteList, searchList ->
            Timber.d(" Size: ${favoriteList.size}")
            uiContentListResultsMapper.map(favoriteList, searchList)
        }
        .onEach {
            Timber.tag(TAG).d("searchResultsFlow: emit = $it")
        }
        .flowOn(coroutineContextController.dispatcherDefault)
        .catch { emit(UIList()) }

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
