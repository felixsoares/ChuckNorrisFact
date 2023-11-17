package com.felix.chucknorrisfact.ui.main_feature.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felix.chucknorrisfact.R
import com.felix.chucknorrisfact.core.domain.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawerContent(
    categories: List<Category>,
    selectedCategory: String?,
    onSelectCategory: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet {
        Text(
            text = stringResource(id = R.string.categories),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
        LazyColumn(
            modifier = Modifier
        ) {
            items(categories) { category ->
                val isSelected = category.name == selectedCategory
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = category.name,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = 20.sp,
                                fontWeight = if (isSelected)
                                    FontWeight.SemiBold
                                else
                                    FontWeight.Normal
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        onSelectCategory(category.name)
                    },
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun MainDrawerContentPreview() {
    MainDrawerContent(
        categories = listOf(
            Category("category1"),
            Category("category2"),
            Category("category3"),
        ),
        selectedCategory = "category1",
        onSelectCategory = {}
    )
}