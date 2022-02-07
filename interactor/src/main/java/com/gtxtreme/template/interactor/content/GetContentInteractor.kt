package com.gtxtreme.template.interactor.content

import com.gtxtreme.template.presentation.base.UIList
import kotlinx.coroutines.flow.Flow

interface GetContentInteractor {

    val searchResults: Flow<UIList>

    suspend fun search(param: String)
}
