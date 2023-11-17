package com.felix.chucknorrisfact.ui.main_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felix.chucknorrisfact.R
import com.felix.chucknorrisfact.core.domain.model.Fact

@Composable
fun FactComponent(
    modifier: Modifier = Modifier,
    fact: Fact,
    isLoadingFact: Boolean,
    onRequestFact: () -> Unit,
    onShareRequest: (Fact) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = fact.value,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (isLoadingFact) {
                CircularProgressIndicator()
            } else {
                OutlinedIconButton(
                    onClick = { onShareRequest(fact) }
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = null)
                }
                FilledIconButton(
                    onClick = { onRequestFact() }
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                }
            }
        }
    }

}

@Preview
@Composable
fun FactComponentPreview() {
    FactComponent(
        fact = Fact(
            id = "1",
            value = "Chuck Norris can divide by zero",
        ),
        isLoadingFact = false,
        onRequestFact = {},
        onShareRequest = {
            Fact(
                id = "1",
                value = "Chuck Norris can divide by zero",
            )
        }
    )
}