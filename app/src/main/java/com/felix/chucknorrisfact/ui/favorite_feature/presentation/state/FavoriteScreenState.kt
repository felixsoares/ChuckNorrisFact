package com.felix.chucknorrisfact.ui.favorite_feature.presentation.state

import com.felix.chucknorrisfact.core.domain.model.Fact

data class FavoriteScreenState(
    val facts: List<Fact> = listOf(),
    val isLoading: Boolean = false,
)
