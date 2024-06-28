package com.felix.chucknorrisfact.ui.favorite_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.favorite_feature.presentation.state.FavoriteScreenState

@Composable
fun FavoriteList(
    modifier: Modifier = Modifier,
    uiState: FavoriteScreenState,
    onItemClick: (Fact) -> Unit,
    onShareRequest: (Fact) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        content = {
            items(uiState.facts.size) { index ->
                val fact = uiState.facts[index]
                FactItem(fact = fact, onFavoriteClick = onItemClick, onShareRequest = onShareRequest)
            }
        }
    )
}