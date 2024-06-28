package com.felix.chucknorrisfact.ui.favorite_feature.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.felix.chucknorrisfact.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTopBar(
    modifier: Modifier = Modifier, onClickBack: () -> Unit
) {
    TopAppBar(modifier = modifier, title = {
        Text(
            text = stringResource(id = R.string.favorite_bar_title),
            style = MaterialTheme.typography.headlineSmall,
        )
    }, navigationIcon = {
        IconButton(onClick = onClickBack) {
            Icon(Icons.Default.ArrowBack, null)
        }
    })
}

@Preview
@Composable
fun FavoriteTopPreview() {
    FavoriteTopBar(onClickBack = {})
}