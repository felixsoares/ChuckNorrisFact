package com.felix.chucknorrisfact.ui.favorite_feature.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.core.util.shareFact
import com.felix.chucknorrisfact.ui.favorite_feature.presentation.components.FavoriteScreenContent
import com.felix.chucknorrisfact.ui.favorite_feature.presentation.components.FavoriteTopBar
import com.felix.chucknorrisfact.ui.favorite_feature.presentation.state.FavoriteScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onClickBack: () -> Unit
) {

    val viewModel: FavoriteViewModel = hiltViewModel()
    val uiState = viewModel.uiState

    val context = LocalContext.current

    Scaffold(
        topBar = {
            FavoriteTopBar(onClickBack = { onClickBack() })
        },
        content = { paddingValues ->
            FavoriteScreenContent(
                modifier = Modifier.padding(paddingValues),
                uiState = uiState,
                onItemClick = {
                    viewModel.onEvent(FavoriteScreenEvent.RemoveFavorite(it))
                },
                onShareRequest = { fact ->
                    context.shareFact(fact.value)
                }
            )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FavoriteScreenPreview() {
    val uiState = FavoriteScreenState(
        facts = listOf(
            Fact(
                id = "1",
                value = "Chuck Norris can divide by zero asndj absdb asbd ajsbd asd asd ans bdkabs dbaksbdlkas bkldasb ldkabs abld abs"
            ),
            Fact(
                id = "2",
                value = "Chuck Norris can slam a revolving door"
            ),
            Fact(
                id = "3",
                value = "Chuck Norris can speak braille"
            )
        )
    )

    Scaffold(
        topBar = {
            FavoriteTopBar(onClickBack = {})
        },
        content = { paddingValues ->
            FavoriteScreenContent(
                modifier = Modifier.padding(paddingValues),
                uiState = uiState,
                onItemClick = {},
                onShareRequest = {}
            )
        }
    )
}