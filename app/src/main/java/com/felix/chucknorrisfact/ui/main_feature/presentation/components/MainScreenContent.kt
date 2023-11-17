package com.felix.chucknorrisfact.ui.main_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.felix.chucknorrisfact.core.domain.model.Fact
import com.felix.chucknorrisfact.ui.main_feature.presentation.state.MainScreenState

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    state: MainScreenState,
    paddingValues: PaddingValues,
    onRequestFact: () -> Unit,
    onRetry: () -> Unit,
    onShareRequest: (Fact) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.isError -> {
                ErrorContent(
                    modifier = Modifier,
                    onRetry = {
                        onRetry()
                    }
                )
            }

            else -> {
                FactComponent(
                    modifier = Modifier,
                    fact = state.fact,
                    isLoadingFact = state.isLoadingFact,
                    onRequestFact = {
                        onRequestFact()
                    },
                    onShareRequest = {
                        onShareRequest(it)
                    },
                )
            }
        }
    }

}

@Preview
@Composable
fun MainScreenContentPreview() {
    MainScreenContent(
        state = MainScreenState(
            isLoading = false,
            isError = false,
            fact = Fact(
                id = "1",
                value = "Chuck Norris can divide by zero.",
            )
        ),
        paddingValues = PaddingValues(),
        onRequestFact = {},
        onRetry = {},
        onShareRequest = {}
    )
}