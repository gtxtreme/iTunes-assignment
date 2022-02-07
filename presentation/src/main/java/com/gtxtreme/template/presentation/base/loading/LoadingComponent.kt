package com.gtxtreme.template.presentation.base.loading

import android.view.View
import com.gtxtreme.template.presentation.R
import com.gtxtreme.template.presentation.base.component.StatefulComponent
import com.gtxtreme.template.presentation.base.extensions.setVisible

class LoadingComponent : StatefulComponent<Boolean>() {

    private val loadingView: View?
        get() = view?.findViewById(R.id.progressBarLayout)

    override fun bindInternal(view: View) = Unit

    override fun setDataInternal(newData: Boolean) {
        loadingView?.setVisible(newData)
    }

    override fun unBindInternal() = Unit
}
