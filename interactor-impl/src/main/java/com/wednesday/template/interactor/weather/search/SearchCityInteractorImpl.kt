package com.wednesday.template.interactor.weather.search

import com.wednesday.template.domain.base.Result
import com.wednesday.template.domain.weather.City
import com.wednesday.template.domain.weather.GetFavouriteCitiesFlowUseCase
import com.wednesday.template.domain.weather.SearchCitiesUseCase
import com.wednesday.template.interactor.base.CoroutineContextController
import com.wednesday.template.interactor.weather.SearchCityInteractor
import com.wednesday.template.presentation.base.UIList
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class SearchCityInteractorImpl(
    private val searchCitiesUseCase: SearchCitiesUseCase,
    private val favouriteCitiesFlowUseCase: GetFavouriteCitiesFlowUseCase,
    private val citySearchResultMapper: UICitySearchResultsMapper,
    private val coroutineContextController: CoroutineContextController
) : SearchCityInteractor {

    private val searchResultStateFlow = MutableSharedFlow<List<City>>()

    override val searchResultsFlow: Flow<UIList> = favouriteCitiesFlowUseCase(Unit)
        .combine(searchResultStateFlow) { favoriteCites, searchResults ->
            citySearchResultMapper.map(favoriteCites, searchResults)
        }
        .onEach {
            Timber.tag(TAG).d("searchResultsFlow: emit = $it")
        }
        .flowOn(coroutineContextController.dispatcherDefault)
        .catch {
            emit(UIList())
        }

    override suspend fun search(term: String): Unit = coroutineScope {
        Timber.tag(TAG).d("search: term = $term")
        val list = when (val citiesResult = searchCitiesUseCase(term)) {
            is Result.Error -> {
                Timber.tag(TAG).e(citiesResult.exception, "search error")
                listOf()
            }
            is Result.Success -> citiesResult.data
        }
        searchResultStateFlow.emit(list)
    }

    companion object {
        private const val TAG = "SearchCityInteractorImpl"
    }
}
