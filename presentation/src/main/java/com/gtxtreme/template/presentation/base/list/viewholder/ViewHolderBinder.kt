package com.gtxtreme.template.presentation.base.list.viewholder

import com.gtxtreme.template.presentation.base.UIListItemBase

interface ViewHolderBinder<T : UIListItemBase> {

    fun onBind(current: T)
}
