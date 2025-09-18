package com.example.myassssmentapplication.ui.dashboard

import com.example.myassssmentapplication.api.Item

data class DashboardUiState(
    val isLoading: Boolean = false,
    val items: List<Item> = emptyList(),
    val error: String? = null
)