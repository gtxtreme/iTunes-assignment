package com.gtxtreme.template.interactor.content

import com.gtxtreme.template.domain.content.Content
import kotlinx.coroutines.flow.Flow

interface GetContentInteractor {

    val searchResults: Flow<List<Content>>

    suspend fun search(param: String)
}
