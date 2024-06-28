package com.felix.chucknorrisfact.ui.favorite_feature.presentation

sealed class FavoriteScreenEvent<out T> {
    class RemoveFavorite<out T>(val data: T) : FavoriteScreenEvent<T>()
    object GetLocalFacts : FavoriteScreenEvent<Nothing>()
}