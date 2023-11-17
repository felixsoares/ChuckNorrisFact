package com.felix.chucknorrisfact.ui.main_feature.presentation

import android.content.Intent
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.felix.chucknorrisfact.ui.main_feature.presentation.components.MainDrawerContent
import com.felix.chucknorrisfact.ui.main_feature.presentation.components.MainScreenContent
import com.felix.chucknorrisfact.ui.main_feature.presentation.components.MainTopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFeatureScreen() {
    val viewModel: MainFeatureViewModel = hiltViewModel()
    val uiState = viewModel.uiState

    val uiDrawerState = viewModel.uiDrawerState
    val categories = uiDrawerState.categories

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = if (categories.isNotEmpty()) {
            {
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
                    }
                )
            }
        } else {
            {}
        }
    ) {
        Scaffold(
            topBar = {
                MainTopBar(
                    selectedCategory = uiDrawerState.selectedCategory,
                    hasCategories = uiDrawerState.categories.isNotEmpty(),
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
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, fact.value)
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    },
                )
            },
        )
    }

}

@Preview
@Composable
fun MainScreenPreview() {
    MainFeatureScreen()
}