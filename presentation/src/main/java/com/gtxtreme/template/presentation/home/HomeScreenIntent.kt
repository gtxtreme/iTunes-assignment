package com.gtxtreme.template.presentation.home

import com.gtxtreme.template.presentation.base.intent.Intent
import com.gtxtreme.template.presentation.content.UIContent

interface HomeScreenIntent : Intent {
    object Search : HomeScreenIntent
    class RemoveFavourite(val item: UIContent) : HomeScreenIntent
}
