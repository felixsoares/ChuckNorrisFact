package com.felix.chucknorrisfact.ui.favorite_feature.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felix.chucknorrisfact.R
import com.felix.chucknorrisfact.core.domain.model.Fact

@Composable
fun FactItem(
    modifier: Modifier = Modifier,
    fact: Fact,
    onFavoriteClick: (Fact) -> Unit,
    onShareRequest: (Fact) -> Unit
) {

    val openAlertDialog = remember { mutableStateOf(false) }

    if (openAlertDialog.value) {
        AlertDialogDeletion(
            onDismissRequest = {
                openAlertDialog.value = false
            },
            onConfirmation = {
                onFavoriteClick(fact)
                openAlertDialog.value = false
            }
        )
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = fact.value,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )

            Spacer(modifier = Modifier.size(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier
                        .clickable { openAlertDialog.value = true },
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        Color.Red
                    )
                )

                Image(
                    modifier = Modifier
                        .clickable { onShareRequest(fact) },
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                )
            }

        }
    }
}

@Composable
fun AlertDialogDeletion(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        icon = {
            Icon(Icons.Default.Delete, contentDescription = null)
        },
        title = {
            Text(text = stringResource(id = R.string.dialog_title))
        },
        text = {
            Text(text = stringResource(id = R.string.dialog_message))
        },
        onDismissRequest = {

        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = stringResource(id = R.string.dialog_positive_button))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = stringResource(id = R.string.dialog_negative_button))
            }
        }
    )
}

@Preview
@Composable
fun FactItemPreview() {
    FactItem(
        fact = Fact(
            id = "1",
            value = "Chuck Norris can divide by zero asndj absdb asbd ajsbd asd asd ans bdkabs dbaksbdlkas bkldasb ldkabs abld abs"
        ),
        onFavoriteClick = {},
        onShareRequest = {}
    )
}