package com.gtxtreme.template.interactor.content.models

import com.gtxtreme.template.domain.content.Content
import com.gtxtreme.template.presentation.content.UIContent

val testContent = Content(
    1,
    "", "Arijit Singh", "Bekhayali", "", 1234
)

val testUiContent = UIContent(
    1,
    "Arijit Singh",
    "Bekhayali", 1234,
    false
)

val testUIFavouriteContent = testUiContent.copy(isFavourite = true)
