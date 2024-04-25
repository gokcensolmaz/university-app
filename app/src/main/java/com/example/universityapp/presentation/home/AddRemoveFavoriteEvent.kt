package com.example.universityapp.presentation.home

import com.example.universityapp.data.local.University

sealed class  AddRemoveFavoriteEvent {
    data class UpsertDeleteUniversity(val university: University): AddRemoveFavoriteEvent()

    object RemoveSideEffect: AddRemoveFavoriteEvent()
}