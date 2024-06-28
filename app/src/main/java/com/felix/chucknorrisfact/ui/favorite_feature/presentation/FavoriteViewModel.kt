package com.felix.chucknorrisfact.ui.favorite_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.domain.usecase.DeleteFactFavoriteUseCase
import com.felix.chucknorrisfact.ui.favorite_feature.domain.usecase.GetFactsFavoriteUseCase
import com.felix.chucknorrisfact.ui.favorite_feature.presentation.state.FavoriteScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteFacts: GetFactsFavoriteUseCase,
    private val deleteFactFavoriteUseCase: DeleteFactFavoriteUseCase
) : ViewModel() {

    var uiState by mutableStateOf(FavoriteScreenState())
        private set

    init {
        onEvent(FavoriteScreenEvent.GetLocalFacts)
    }

    fun onEvent(event: FavoriteScreenEvent<Fact>) {
        when (event) {
            is FavoriteScreenEvent.GetLocalFacts -> {
                getLocalFacts()
            }

            is FavoriteScreenEvent.RemoveFavorite -> {
                removeFavorite(event.data)
            }
        }
    }

    private fun removeFavorite(fact: Fact) {
        viewModelScope.launch {
            deleteFactFavoriteUseCase.invoke(fact)
        }
    }

    private fun getLocalFacts() {
        viewModelScope.launch {
            uiState = uiState.copy(
                isLoading = true
            )

            getFavoriteFacts.invoke().collectLatest { facts ->
                uiState = uiState.copy(
                    facts = facts,
                    isLoading = false
                )
            }
        }
    }

}