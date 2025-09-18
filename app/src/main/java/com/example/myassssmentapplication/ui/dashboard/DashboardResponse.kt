package com.example.myassssmentapplication.ui.dashboard

import com.example.myassssmentapplication.api.Item



data class DashboardResponse(
    val entities: List<Item> = emptyList(), // match the JSON key
    val entityTotal: Int
)