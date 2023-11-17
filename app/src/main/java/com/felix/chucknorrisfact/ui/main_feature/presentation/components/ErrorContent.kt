package com.felix.chucknorrisfact.ui.main_feature.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felix.chucknorrisfact.R

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.error_message),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { onRetry() }) {
            Text(text = stringResource(id = R.string.retry))
        }

    }

}

@Preview
@Composable
fun ErrorContentPreview() {
    ErrorContent(
        onRetry = {}
    )
}