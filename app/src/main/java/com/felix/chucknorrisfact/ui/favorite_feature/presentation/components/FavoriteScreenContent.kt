package com.felix.chucknorrisfact.ui.favorite_feature.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.presentation.state.FavoriteScreenState

@Composable
fun FavoriteScreenContent(
    modifier: Modifier = Modifier,
    uiState: FavoriteScreenState,
    onItemClick: (Fact) -> Unit,
    onShareRequest: (Fact) -> Unit
) {
    when {
        uiState.isLoading -> {
            LoadingContent()
        }

        uiState.facts.isEmpty() -> {
            EmptyContent()
        }

        else -> {
            FavoriteList(modifier, uiState, onItemClick, onShareRequest)
        }
    }
}

@Preview
@Composable
fun FavoriteScreenContentPreview() {
    FavoriteScreenContent(
        uiState = FavoriteScreenState(
            facts = emptyList(),
            isLoading = true
        ),
        onItemClick = {},
        onShareRequest = {}
    )
}