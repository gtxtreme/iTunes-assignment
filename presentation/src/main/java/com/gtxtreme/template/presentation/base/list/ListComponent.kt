package com.gtxtreme.template.presentation.base.list

import androidx.lifecycle.ViewModel // ktlint-disable import-ordering
import com.gtxtreme.template.presentation.R
import com.gtxtreme.template.presentation.base.UIListItemBase
import com.gtxtreme.template.presentation.base.intent.Intent
import com.gtxtreme.template.presentation.base.intent.IntentHandler
import com.gtxtreme.template.presentation.base.list.renderer.ListItemRenderer
import com.gtxtreme.template.presentation.base.UIListItemBase
import com.gtxtreme.template.presentation.base.intent.Intent
import com.gtxtreme.template.presentation.base.intent.IntentHandler
import com.gtxtreme.template.presentation.base.list.renderer.ListItemRenderer

internal class ListComponent<T, I : Intent>(
    listViewModel: T,
    recyclerViewId: Int = R.id.recyclerView,
    callback: ListComponent<T, I>.() -> Unit,
) : BaseListComponent<T, I>(
    listViewModel,
    recyclerViewId
) where T : ViewModel, T : IntentHandler<I> {

    init {
        callback(this)
    }

    @Suppress("UNCHECKED_CAST")
    internal inline fun <reified T : UIListItemBase> addRenderer(renderer: ListItemRenderer<T>) {
        renderers.add(T::class to (renderer as ListItemRenderer<UIListItemBase>))
    }
}
