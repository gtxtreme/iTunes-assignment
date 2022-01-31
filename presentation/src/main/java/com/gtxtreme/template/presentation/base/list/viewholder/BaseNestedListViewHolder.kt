package com.gtxtreme.template.presentation.base.list.viewholder

import androidx.annotation.CallSuper // ktlint-disable import-ordering
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.gtxtreme.template.presentation.R
import com.wednesday.template.presentation.base.UIListItemBase
import com.gtxtreme.template.presentation.base.list.ListAdapter
import com.gtxtreme.template.presentation.base.list.renderer.ListItemRenderer
import kotlin.reflect.KClass

abstract class BaseNestedListViewHolder<T : UIListItemBase>(
    viewBinding: ViewBinding
) : BaseViewHolder<T>(viewBinding) {

    protected val renderers: MutableList<Pair<KClass<*>, ListItemRenderer<UIListItemBase>>> = mutableListOf()

    private val nestedRecyclerView: RecyclerView
        get() = itemView.findViewById(R.id.nestedRecyclerView)

    abstract fun getNestedListItems(item: T): List<UIListItemBase>

    @Suppress("UNCHECKED_CAST")
    protected inline fun <reified T : UIListItemBase> addRenderer(renderer: ListItemRenderer<T>) {
        renderers.add(T::class to (renderer as ListItemRenderer<UIListItemBase>))
    }

    fun setNestedViewHolderPool(viewPool: RecyclerView.RecycledViewPool) = nestedRecyclerView.apply {
        nestedRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
        nestedRecyclerView.adapter = ListAdapter(intentChannel, renderers)
        nestedRecyclerView.setRecycledViewPool(viewPool)
    }

    @CallSuper
    override fun onBindInternal() {
        setNestedListData(getNestedListItems(item))
    }

    private fun setNestedListData(newItems: List<UIListItemBase>) {
        (nestedRecyclerView.adapter as? ListAdapter?)?.apply {
            updateData(newItems)
            notifyDataSetChanged()
        }
    }
}
