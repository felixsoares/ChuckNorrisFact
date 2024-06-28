package com.felix.chucknorrisfact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.felix.chucknorrisfact.core.presentation.navigation.NavigationGraph
import com.felix.chucknorrisfact.theme.ChuckNorrisFactTheme
import com.felix.chucknorrisfact.ui.main_feature.presentation.MainFeatureScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChuckNorrisFactTheme {
                NavigationGraph(navController = rememberNavController())
            }
        }
    }
}