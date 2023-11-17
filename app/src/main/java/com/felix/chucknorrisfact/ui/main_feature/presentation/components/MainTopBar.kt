package com.felix.chucknorrisfact.ui.main_feature.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.felix.chucknorrisfact.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    selectedCategory: String?,
    hasCategories: Boolean,
    onClickMenuIcon: () -> Unit
) {
    val appBarTitle = if (selectedCategory != null) {
        stringResource(
            id = R.string.app_bar_title_with_option,
            selectedCategory
        )
    } else {
        stringResource(id = R.string.app_bar_title)
    }

    TopAppBar(
        title = {
            Text(
                text = appBarTitle,
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        navigationIcon = if (hasCategories) {
            {
                IconButton(
                    onClick = {
                        onClickMenuIcon()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                    )
                }
            }
        } else {
            { }
        }
    )
}

@Preview
@Composable
fun MainTopBarPreview() {

}