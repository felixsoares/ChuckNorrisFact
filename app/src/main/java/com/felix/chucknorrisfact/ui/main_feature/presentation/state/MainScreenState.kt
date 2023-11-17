package com.felix.chucknorrisfact.ui.main_feature.presentation.state

import com.felix.chucknorrisfact.core.domain.model.Fact

data class MainScreenState(
    val fact: Fact = Fact(),
    val isLoadingFact: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
