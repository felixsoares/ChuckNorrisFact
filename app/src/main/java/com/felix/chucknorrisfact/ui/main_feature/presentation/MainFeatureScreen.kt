package com.felix.chucknorrisfact.ui.main_feature.presentation

import android.content.Intent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.felix.chucknorrisfact.core.util.shareFact
import com.felix.chucknorrisfact.ui.main_feature.presentation.components.MainDrawerContent
import com.felix.chucknorrisfact.ui.main_feature.presentation.components.MainScreenContent
import com.felix.chucknorrisfact.ui.main_feature.presentation.components.MainTopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFeatureScreen(
    navigateToFavorites: () -> Unit
) {
    val viewModel: MainFeatureViewModel = hiltViewModel()
    val uiState = viewModel.uiState

    val uiDrawerState = viewModel.uiDrawerState

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MainDrawerContent(
                categories = uiDrawerState.categories,
                selectedCategory = uiDrawerState.selectedCategory,
                onSelectCategory = { category ->
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                    viewModel.onCategorySelected(category)
                },
                navigateToFavorites = { navigateToFavorites() }
            )
        }
    ) {
        Scaffold(
            topBar = {
                MainTopBar(
                    selectedCategory = uiDrawerState.selectedCategory,
                    onClickMenuIcon = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            },
            content = { paddingValues ->
                MainScreenContent(
                    state = uiState,
                    paddingValues = paddingValues,
                    onRequestFact = {
                        viewModel.onEvent(MainScreenEvent.GetFact)
                    },
                    onRetry = {
                        viewModel.onEvent(MainScreenEvent.InitOrRetry)
                    },
                    onShareRequest = { fact ->
                        context.shareFact(fact.value)
                    },
                    onSelectFavorite = {
                        viewModel.onEvent(MainScreenEvent.SaveOrDeleteFactFavorite)
                    }
                )
            },
        )
    }

}

@Preview
@Composable
fun MainScreenPreview() {
    MainFeatureScreen(navigateToFavorites = {})
}