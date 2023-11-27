package com.felix.chucknorrisfact.ui.main_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.chucknorrisfact.core.util.ResultState
import com.felix.chucknorrisfact.ui.main_feature.domain.usecase.GetCategoriesUseCase
import com.felix.chucknorrisfact.ui.main_feature.domain.usecase.GetFactUseCase
import com.felix.chucknorrisfact.ui.main_feature.presentation.state.MainDrawerScreenState
import com.felix.chucknorrisfact.ui.main_feature.presentation.state.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFeatureViewModel @Inject constructor(
    private val getFactUseCase: GetFactUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MainScreenState())
        private set

    var uiDrawerState by mutableStateOf(MainDrawerScreenState())
        private set

    init {
        onEvent(MainScreenEvent.InitOrRetry)
    }

    fun onCategorySelected(category: String?) {
        uiDrawerState = if (category != null && category == uiDrawerState.selectedCategory) {
            uiDrawerState.copy(selectedCategory = null)
        } else {
            uiDrawerState.copy(selectedCategory = category)
        }

        onEvent(MainScreenEvent.GetFact)
    }

    fun onEvent(mainScreenEvent: MainScreenEvent) {
        viewModelScope.launch {
            when (mainScreenEvent) {
                is MainScreenEvent.InitOrRetry -> {
                    uiState = uiState.copy(isLoading = true)

                    getCategoriesUseCase
                        .invoke()
                        .collectLatest { resultState ->
                            if (resultState is ResultState.Success) {
                                uiDrawerState = uiDrawerState.copy(
                                    categories = resultState.data
                                )
                                uiDrawerState = uiDrawerState.copy(
                                    selectedCategory = uiDrawerState.categories.firstOrNull()?.name
                                )
                            }
                        }

                    getFactUseCase
                        .invoke(GetFactUseCase.Params(uiDrawerState.selectedCategory))
                        .collectLatest { resultState ->
                            when (resultState) {
                                is ResultState.Success -> {
                                    uiState = uiState.copy(
                                        fact = resultState.data,
                                        isLoadingFact = false,
                                        isLoading = false,
                                        isError = false
                                    )
                                }

                                is ResultState.Error -> {
                                    uiState = uiState.copy(
                                        isLoading = false,
                                        isLoadingFact = false,
                                        isError = true
                                    )
                                }

                                else -> Unit
                            }
                        }
                }

                is MainScreenEvent.GetFact -> {
                    uiState = uiState.copy(isLoadingFact = true)

                    getFactUseCase.invoke(
                        GetFactUseCase.Params(uiDrawerState.selectedCategory)
                    ).collectLatest { resultState ->
                        when (resultState) {
                            is ResultState.Success -> {
                                uiState = uiState.copy(
                                    fact = resultState.data,
                                    isLoadingFact = false,
                                    isLoading = false,
                                    isError = false
                                )
                            }

                            is ResultState.Error -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    isLoadingFact = false,
                                    isError = true
                                )
                            }

                            else -> Unit
                        }
                    }
                }
            }
        }
    }

}