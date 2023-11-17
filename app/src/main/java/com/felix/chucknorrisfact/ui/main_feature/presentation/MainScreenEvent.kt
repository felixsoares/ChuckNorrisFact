package com.felix.chucknorrisfact.ui.main_feature.presentation

sealed class MainScreenEvent {
    object InitOrRetry : MainScreenEvent()
    object GetFact : MainScreenEvent()
}
