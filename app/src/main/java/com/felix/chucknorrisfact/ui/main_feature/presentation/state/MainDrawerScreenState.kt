package com.felix.chucknorrisfact.ui.main_feature.presentation.state

import com.felix.chucknorrisfact.core.domain.model.Category

data class MainDrawerScreenState(
    val categories: List<Category> = emptyList(),
    val selectedCategory: String? = null,
)
