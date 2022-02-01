package com.gtxtreme.template.presentation.base.snackbar

import android.view.View
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.gtxtreme.template.presentation.R
import com.gtxtreme.template.presentation.base.component.Component
import com.gtxtreme.template.presentation.base.effect.ShowSnackbarEffect
import com.gtxtreme.template.presentation.base.extensions.asString

class SnackbarComponent(
    private val fragment: Fragment
) : Component<ShowSnackbarEffect>() {

    override fun bindInternal(view: View) = Unit

    override fun setData(data: ShowSnackbarEffect) {
        val rootView: View = fragment.requireActivity().run {
            findViewById(R.id.snackbarRoot) ?: window.decorView.rootView
        }

        Snackbar.make(
            rootView,
            data.message.asString(),
            Snackbar.LENGTH_SHORT
        ).apply {
            if (data.action != null) {
                setAction(data.action.asString(), data.onActionClick)
            }
        }.show()
    }

    override fun unbindInternal() = Unit
}
