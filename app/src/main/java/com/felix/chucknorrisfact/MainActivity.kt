package com.felix.chucknorrisfact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.felix.chucknorrisfact.ui.main_feature.presentation.MainFeatureScreen
import com.felix.chucknorrisfact.ui.theme.ChuckNorrisFactTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChuckNorrisFactTheme {
                MainFeatureScreen()
            }
        }
    }
}